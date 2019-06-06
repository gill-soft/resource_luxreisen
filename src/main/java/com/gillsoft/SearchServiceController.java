package com.gillsoft;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.gillsoft.abstract_rest_service.SimpleAbstractTripSearchService;
import com.gillsoft.cache.CacheHandler;
import com.gillsoft.client.ResponseError;
import com.gillsoft.client.RestClient;
import com.gillsoft.client.TripPackage;
import com.gillsoft.model.Document;
import com.gillsoft.model.Lang;
import com.gillsoft.model.Locality;
import com.gillsoft.model.Organisation;
import com.gillsoft.model.RequiredField;
import com.gillsoft.model.RestError;
import com.gillsoft.model.ReturnCondition;
import com.gillsoft.model.Route;
import com.gillsoft.model.Seat;
import com.gillsoft.model.SeatsScheme;
import com.gillsoft.model.Segment;
import com.gillsoft.model.Tariff;
import com.gillsoft.model.TripContainer;
import com.gillsoft.model.Vehicle;
import com.gillsoft.model.request.TripSearchRequest;
import com.gillsoft.model.response.TripSearchResponse;

@RestController
public class SearchServiceController extends SimpleAbstractTripSearchService<TripPackage> {
	
	@Autowired
	private RestClient client;
	
	@Autowired
	@Qualifier("MemoryCacheHandler")
	private CacheHandler cache;

	@Override
	public List<ReturnCondition> getConditionsResponse(String arg0, String arg1) {
		return client.getReturnConditions(arg0);
	}

	@Override
	public List<Document> getDocumentsResponse(String arg0) {
		throw RestClient.createUnavailableMethod();
	}

	@Override
	public List<Tariff> getTariffsResponse(String arg0) {
		return client.getTariffs(arg0);
	}

	@Override
	public List<RequiredField> getRequiredFieldsResponse(String arg0) {
		throw RestClient.createUnavailableMethod();
	}

	@Override
	public Route getRouteResponse(String arg0) {
		throw RestClient.createUnavailableMethod();
	}

	@Override
	public SeatsScheme getSeatsSchemeResponse(String arg0) {
		throw RestClient.createUnavailableMethod();
	}
	
	@Override
	public List<Seat> updateSeatsResponse(String arg0, List<Seat> arg1) {
		throw RestClient.createUnavailableMethod();
	}
	
	@Override
	public List<Seat> getSeatsResponse(String tripId) {
		try {
			return client.getSeats(tripId);
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}
	}

	@Override
	public TripSearchResponse initSearchResponse(TripSearchRequest request) {
		return simpleInitSearchResponse(cache, request);
	}

	@Override
	public void addInitSearchCallables(List<Callable<TripPackage>> callables, TripSearchRequest request) {
		callables.add(() -> {
			try {
				validateSearchParams(request);
				TripPackage tripPackage = client.getCachedTrips(request);
				if (tripPackage == null) {
					throw new ResponseError("Empty result");
				}
				tripPackage.setRequest(request);
				return tripPackage;
			} catch (ResponseError e) {
				return new TripPackage();
			} catch (Exception e) {
				return null;
			}
		});
	}

	private void validateSearchParams(TripSearchRequest request) throws ResponseError {
		if (request.getDates().get(0) == null
				|| request.getDates().get(0).getTime() < DateUtils.truncate(new Date(), Calendar.DATE).getTime()) {
			throw new ResponseError("Invalid parameter \"date\" [" + request.getDates().get(0) + ']');
		}
		if (request.getLocalityPairs().get(0) == null || request.getLocalityPairs().get(0).length != 2) {
			throw new ResponseError("Invalid parameter \"pair\" [" + request.getLocalityPairs().get(0) + ']');
		}
		if (request.getCurrency() != null && !client.isCorrectCurrency(request.getCurrency())) {
			throw new ResponseError("Invalid parameter \"currency\" [" + request.getCurrency() + ']');
		}
	}

	@Override
	public TripSearchResponse getSearchResultResponse(String searchId) {
		return simpleGetSearchResponse(cache, searchId);
	}

	@Override
	public void addNextGetSearchCallablesAndResult(List<Callable<TripPackage>> callables, Map<String, Vehicle> vehicles,
			Map<String, Locality> localities, Map<String, Organisation> organisations, Map<String, Segment> segments,
			List<TripContainer> containers, TripPackage tripPackage) {
		if (!tripPackage.isContinueSearch()) {
			addResult(vehicles, localities, segments, containers, tripPackage);
		} else if (tripPackage.getRequest() != null) {
			addInitSearchCallables(callables, tripPackage.getRequest().getLocalityPairs().get(0),
					tripPackage.getRequest().getDates().get(0));
		}
	}

	private void addResult(Map<String, Vehicle> vehicles, Map<String, Locality> localities,
			Map<String, Segment> segments, List<TripContainer> containers, TripPackage tripPackage) {
		TripContainer container = new TripContainer();
		container.setRequest(tripPackage.getRequest());
		if (tripPackage.getRouteList() != null) {
			container.setTrips(tripPackage.getRouteList().stream()
					.map(com.gillsoft.client.model.info.route.Route::getTrip).collect(Collectors.toList()));
			segments.putAll(
					tripPackage.getRouteList().stream().map(com.gillsoft.client.model.info.route.Route::getSegment)
							.collect(Collectors.toMap(Segment::getId, s -> s)));
			addLocalities(segments, localities);
			addVehicles(segments, vehicles);
		}
		if (tripPackage.getError() != null) {
			container.setError(new RestError(tripPackage.getError().getMessage()));
		}
		containers.add(container);
	}

	private void addLocalities(Map<String, Segment> segments, Map<String, Locality> localities) {
		if (!segments.isEmpty()) {
			localities.putAll(segments.entrySet().stream().map(Map.Entry::getValue)
					.map(segment -> java.util.Arrays.asList(segment.getDeparture(),
							segment.getDeparture().getParent(),
							segment.getDeparture().getParent() != null ? segment.getDeparture().getParent().getParent() : null,
							segment.getArrival(), segment.getArrival().getParent(),
							segment.getArrival().getParent() != null ? segment.getArrival().getParent().getParent() : null))
					.flatMap(java.util.List::stream)
					.filter(java.util.Objects::nonNull)
					.collect(Collectors.toMap(Locality::getId, t -> {
						try {
							Locality clone = t.clone();
							if (clone.getParent() != null) {
								clone.setParent(new Locality(clone.getParent().getId()));
							}
							return clone;
						} catch (CloneNotSupportedException e) {
							return null;
						}
					}, (locality1, locality2) -> { return locality1; })));
			segments.entrySet().stream().map(Map.Entry::getValue).forEach(segment -> {
				if (segment.getDeparture() != null) {
					segment.getDeparture().setName((ConcurrentMap<Lang, String>)null);
					segment.getDeparture().setParent(null);
				}
				if (segment.getArrival() != null) {
					segment.getArrival().setName((ConcurrentMap<Lang, String>)null);
					segment.getArrival().setParent(null);
				}
			});
		}
	}

	private void addVehicles(Map<String, Segment> segments, Map<String, Vehicle> vehicles) {
		if (!segments.isEmpty()) {
			segments.entrySet().stream().map(Map.Entry::getValue).forEach(segment -> {
				if (segment.getVehicle() != null) {
					vehicles.put(segment.getVehicle().getId(), segment.getVehicle());
					segment.setVehicle(new Vehicle(String.valueOf(segment.getVehicle().getId())));
				}
			});
		}
	}

}
