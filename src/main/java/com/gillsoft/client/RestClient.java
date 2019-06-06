package com.gillsoft.client;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.gillsoft.cache.CacheHandler;
import com.gillsoft.cache.IOCacheException;
import com.gillsoft.cache.RedisMemoryCache;
import com.gillsoft.client.model.Auth;
import com.gillsoft.client.model.Response;
import com.gillsoft.client.model.booking.order.Discount;
import com.gillsoft.client.model.booking.order.NewOrderRequest;
import com.gillsoft.client.model.booking.order.NewOrderResponse;
import com.gillsoft.client.model.booking.order.Passenger;
import com.gillsoft.client.model.booking.order.Relations;
import com.gillsoft.client.model.info.CitiesResponse;
import com.gillsoft.client.model.info.CurrenciesResponse;
import com.gillsoft.client.model.info.details.DetailsResponse;
import com.gillsoft.client.model.info.route.Route;
import com.gillsoft.client.model.info.route.RouteResponse;
import com.gillsoft.client.model.info.route.discounts.RouteDiscountsResponse;
import com.gillsoft.client.model.info.seats.FreeSeatsResponse;
import com.gillsoft.client.model.info.seats.SeatIdModel;
import com.gillsoft.client.model.info.ticket.CalculationResponse;
import com.gillsoft.logging.SimpleRequestResponseLoggingInterceptor;
import com.gillsoft.model.Currency;
import com.gillsoft.model.Customer;
import com.gillsoft.model.Lang;
import com.gillsoft.model.Locality;
import com.gillsoft.model.RestError;
import com.gillsoft.model.ReturnCondition;
import com.gillsoft.model.Seat;
import com.gillsoft.model.ServiceItem;
import com.gillsoft.model.Tariff;
import com.gillsoft.model.request.TripSearchRequest;
import com.gillsoft.util.RestTemplateUtil;
import com.gillsoft.util.StringUtil;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RestClient {

	private static final Logger LOGGER = LogManager.getLogger(RestClient.class);

	public static final String STATIONS_CACHE_KEY = "luxreisen.stations.";
	public static final String TRIPS_CACHE_KEY = "luxreisen.trips.";

	public static final String CODE_DEPARTURE = "D";
	public static final String CODE_ARRIVE = "A";

	// AUTH
	private static final URI AUTH_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/oauth/token").build().toUri();
	// INFO
	private static final URI INFO_CITIES_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/api/info/get_cities").build().toUri();
	//private static final URI COUNTRIES_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/api/info/get_countries").build().toUri();
	private static final URI INFO_CURRENCIES_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/api/info/get_currencies").build().toUri();
	private static final URI INFO_ROUTES_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/api/info/get_routes").build().toUri();
	private static final URI INFO_TRIP_DETAILS_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/api/info/get_trip_details").build().toUri();
	private static final URI INFO_FREE_SEATS_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/api/info/get_free_seats").build().toUri();
	private static final URI INFO_ROUTE_DISCOUNTS_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/api/info/get_route_discounts").build().toUri();
	private static final URI INFO_TICKET_PRICE_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/api/info/get_ticket_price").build().toUri();
	//BOOKING
	private static final URI BOOKING_NEW_ORDER_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/api/booking/new_order").build().toUri();
	private static final URI BOOKING_PAY_ORDER_URI = UriComponentsBuilder.fromUriString(Config.getUrl() + "/api/booking/pay_order").build().toUri();
	
	private static HttpHeaders headers = new HttpHeaders();
	private List<String> currencies = new ArrayList<>();

    @Autowired
    @Qualifier("RedisMemoryCache")
	private CacheHandler cache;

	private RestTemplate template;
	// для запросов поиска с меньшим таймаутом
	private RestTemplate searchTemplate;

	private Auth auth;

	public RestClient() {
		template = createNewPoolingTemplate(Config.getRequestTimeout());
		searchTemplate = createNewPoolingTemplate(Config.getSearchRequestTimeout());
		getToken();
		schedule(this::getCurrencies, TimeUnit.SECONDS, 1);
	}

	public RestTemplate createNewPoolingTemplate(int requestTimeout) {
		HttpComponentsClientHttpRequestFactory factory = (HttpComponentsClientHttpRequestFactory) RestTemplateUtil
				.createPoolingFactory(Config.getUrl(), 300, requestTimeout);
		factory.setReadTimeout(Config.getReadTimeout());
		RestTemplate template = new RestTemplate(new BufferingClientHttpRequestFactory(factory));
		template.setInterceptors(Collections.singletonList(new SimpleRequestResponseLoggingInterceptor()));
		return template;
	}

	public void getToken() {
		if (auth == null || auth.isExpired()) {
			try {
				auth = getResult(template, getAuthRequest(), Auth.class, HttpMethod.POST, AUTH_URI);
				if (auth != null) {
					headers.set(HttpHeaders.AUTHORIZATION, auth.getToken());
					headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
					schedule(this::getToken, TimeUnit.SECONDS, auth.getExpiresIn());
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	private HttpEntity<MultiValueMap<String, String>> getAuthRequest() {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", Config.getGrantType());
		body.add("client_id", Config.getClientId());
		body.add("client_secret", Config.getSecret());
		if (auth == null || !auth.isExpired()) {
			body.add("username", Config.getLogin());
			body.add("password", Config.getPassword());
		} else {
			body.add("refresh_token", auth.getRefreshToken());
		}
		return new HttpEntity<>(body, headers);
	}

	//*** CURRENCIES
	public void getCurrencies() {
		try {
			CurrenciesResponse currenciesResponse = getResult(template, new HttpEntity<>(headers),
					CurrenciesResponse.class, HttpMethod.GET, INFO_CURRENCIES_URI);
			if (currenciesResponse != null && currenciesResponse.getCurrencies() != null) {
				currencies = currenciesResponse.getCurrencies();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		schedule(this::getCurrencies, TimeUnit.HOURS, 2);
	}

	public boolean isCorrectCurrency(Currency currency) {
		return currencies != null && !currencies.isEmpty() && currencies.contains(currency.toString());
	}

	private void schedule(Runnable runnable, TimeUnit delayTimeUnit, long delay) {
		new Timer().schedule(new TimerTask() {
		    @Override
		    public void run() {
		    	runnable.run();
		    }
		}, delayTimeUnit.toMillis(delay));
	}

	//*** STATIONS
	@SuppressWarnings("unchecked")
	public List<Locality> getCachedStations() throws IOCacheException {
		Map<String, Object> params = new HashMap<>();
		params.put(RedisMemoryCache.OBJECT_NAME, RestClient.STATIONS_CACHE_KEY);
		params.put(RedisMemoryCache.IGNORE_AGE, true);
		params.put(RedisMemoryCache.UPDATE_DELAY, Config.getCacheStationsUpdateDelay());
		params.put(RedisMemoryCache.UPDATE_TASK, new UpdateStationsTask());
		return (List<Locality>) cache.read(params);
	}

	public List<Locality> getAllStations() throws ResponseError {
		Map<String, Locality> cities = new HashMap<>();
		Map<Integer, Locality> countries = new HashMap<>();
		try {
			Arrays.asList("en", "pl", "uk").forEach(lang -> {
				try {
					CitiesResponse citiesResponse = getResult(template, getCitiesRequest(lang), CitiesResponse.class,
							HttpMethod.GET, INFO_CITIES_URI);
					if (citiesResponse != null
							&& citiesResponse.getCities() != null) {
						Lang curLang = "uk".equals(lang) ? Lang.UA : Lang.valueOf(lang.toUpperCase());
						citiesResponse.getCities().stream().forEach(city -> {
							Locality locality = cities.get(String.valueOf(city.getId()));
							if (locality == null) {
								locality = new Locality(String.valueOf(city.getId()));
								locality.setName(curLang, city.getName());
								cities.put(locality.getId(), locality);
							} else {
								locality.setName(curLang, city.getName());
							}
							if (city.getCountryId() != null) {
								Locality country = countries.get(city.getCountryId());
								String countryId = StringUtil.md5(String.valueOf(city.getCountryId()));
								if (country == null) {
									country = new Locality(countryId);
									countries.put(city.getCountryId(), country);
								}
								country.setName(curLang, city.getCountryName());
								locality.setParent(country);
							}
						});
					}
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
			});
			return new ArrayList<>(cities.values());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ResponseError(e.getMessage(), e);
		}
	}

	private HttpEntity<MultiValueMap<String, String>> getCitiesRequest(String lang) {
		HttpHeaders localityHeaders = new HttpHeaders();
		localityHeaders.addAll(headers);
		localityHeaders.set(HttpHeaders.ACCEPT_LANGUAGE, lang);
		localityHeaders.set(HttpHeaders.CONTENT_LANGUAGE, lang);
		return new HttpEntity<>(localityHeaders);
	}

	//*** TRIPS
	public TripPackage getCachedTrips(TripSearchRequest request) throws ResponseError {
		Map<String, Object> params = new HashMap<>();
		params.put(RedisMemoryCache.OBJECT_NAME, UpdateTripsTask.getKey(request));
		params.put(RedisMemoryCache.UPDATE_TASK, new UpdateTripsTask(request));
		try {
			return (TripPackage) cache.read(params);
		} catch (IOCacheException e) {
			LOGGER.error(e.getMessage(), e);
			// ставим пометку, что кэш еще формируется
			TripPackage tripPackage = new TripPackage();
			tripPackage.setContinueSearch(true);
			return tripPackage;
		} catch (Exception e) {
			throw new ResponseError(e.getMessage());
		}
	}

	public TripPackage getTrips(TripSearchRequest request) throws ResponseError {
		RouteResponse routeResponse = getResult(searchTemplate, getTripSearchRequest(request), RouteResponse.class,
				HttpMethod.POST, INFO_ROUTES_URI);
		TripPackage tripPackage = new TripPackage();
		if (routeResponse != null && routeResponse.getRoutes() != null && !routeResponse.getRoutes().isEmpty()) {
			tripPackage.setFrom(request.getLocalityPairs().get(0)[0]);
			tripPackage.setTo(request.getLocalityPairs().get(0)[1]);
			tripPackage.setRouteList(routeResponse.getRoutes());
			getRouteDetails(routeResponse.getRoutes());
		}
		return tripPackage;
	}

	private void getRouteDetails(List<Route> routes) {
		List<Thread> runnableList = new ArrayList<>();
		routes.forEach(route -> {
			List<Thread> routeRunnableList = new ArrayList<>();
			HttpEntity<MultiValueMap<String, String>> tripSearchRequest = getTripDetailsRequest(route.getRouteId());
			routeRunnableList.add(new Thread(() -> {
				try {
					route.setTripDetails(getResult(searchTemplate, tripSearchRequest, DetailsResponse.class, HttpMethod.POST,
							INFO_TRIP_DETAILS_URI).getTripDetails());
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
				}
			}));
			routeRunnableList.add(new Thread(() -> {
				try {
					route.setFreeSeatsList(getResult(searchTemplate, tripSearchRequest, FreeSeatsResponse.class,
							HttpMethod.POST, INFO_FREE_SEATS_URI).getFreeSeats());
				} catch (Exception e) {

				}
			}));
			routeRunnableList.add(new Thread(() -> {
				try {
					route.setRouteDiscounts(getResult(searchTemplate, tripSearchRequest, RouteDiscountsResponse.class,
							HttpMethod.POST, INFO_ROUTE_DISCOUNTS_URI).getDiscounts());
				} catch (Exception e) {

				}
			}));
			routeRunnableList.add(new Thread(() -> {
				try {
					route.setTicketPrice(getResult(searchTemplate, tripSearchRequest, CalculationResponse.class,
							HttpMethod.POST, INFO_TICKET_PRICE_URI).getCalculation());
				} catch (Exception e) {

				}
			}));
			routeRunnableList.forEach(Thread::start);
			runnableList.addAll(routeRunnableList);
		});
		while (runnableList.stream().filter(Thread::isAlive).count() != 0) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				
			}
		}
	}

	private HttpEntity<MultiValueMap<String, String>> getTripSearchRequest(TripSearchRequest request) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("from_city_id", request.getLocalityPairs().get(0)[0]);
		map.add("to_city_id", request.getLocalityPairs().get(0)[1]);
		map.add("departure_date", StringUtil.dateFormat.format(request.getDates().get(0)));
		if (request.getCurrency() != null) {
			map.add("currency", request.getCurrency().toString());
		} else if (Config.getCurrency() != null && !Config.getCurrency().isEmpty()) {
			map.add("currency", Config.getCurrency());
		}
		return new HttpEntity<>(map, headers);
	}

	private HttpEntity<MultiValueMap<String, String>> getTripDetailsRequest(String routeId) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("route_id", routeId);
		return new HttpEntity<>(map, headers);
	}

	public Route getRoute(String routeId) {
		Map<String, Object> params = new HashMap<>();
		params.put(RedisMemoryCache.OBJECT_NAME, routeId);
		try {
			String redisKey = (String) cache.read(params);
			params.put(RedisMemoryCache.OBJECT_NAME, redisKey);
			return ((TripPackage)cache.read(params)).getRouteList().stream().filter(route -> route.getRouteId().equals(routeId)).findFirst().get();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	//*** SEATS
	public List<Seat> getSeats(String routeId) throws ResponseError {
		List<Seat> seats = new ArrayList<>();
		try {
			seats = getResult(template, getTripDetailsRequest(routeId), FreeSeatsResponse.class, HttpMethod.POST,
					INFO_FREE_SEATS_URI).getFreeSeats().stream().map(com.gillsoft.client.model.info.seats.FreeSeat::toSeats)
							.flatMap(java.util.List::stream).collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return seats;
	}

	//*** RETURN CONDITIONS
	public List<ReturnCondition> getReturnConditions(String routeId) {
		List<ReturnCondition> returnConditions = new ArrayList<>();
		Route route = getRoute(routeId);
		if (route != null && route.getTripDetails() != null && route.getTripDetails().getRefunds() != null
				&& route.getTripDetails().getRefunds().getDefinitionList() != null) {
			route.getTripDetails().getRefunds().getDefinitionList().forEach(condition -> {
				ReturnCondition returnCondition = new ReturnCondition();
				returnCondition.setId(
						String.valueOf(route.getTripDetails().getRefunds().getDefinitionList().indexOf(condition)));
				returnCondition.setMinutesBeforeDepart(
						(condition.getFrom() != null ? condition.getFrom() : condition.getTo()) * 60);
				returnCondition.setReturnPercent(new BigDecimal(condition.getReturnRate()));
				if (condition.getFrom() != null) {
					returnCondition.setDescription(Lang.EN,
							"From " + condition.getFrom() + " to " + condition.getTo() + " hours before depart");
				} else {
					returnCondition.setDescription(Lang.EN, "More than " + condition.getTo() + " hours before depart");
				}
				returnCondition.setTitle(returnCondition.getDescription());
				returnConditions.add(returnCondition);
			});
		}
		return returnConditions;
	}

	public List<Tariff> getTariffs(String routeId) {
		List<Tariff> tariffs = new ArrayList<>();
		Route route = getRoute(routeId);
		if (route != null && route.getRouteDiscounts() != null) {
			route.getRouteDiscounts().stream().forEach(discount -> {
				Tariff tariff = new Tariff();
				tariff.setId(String.valueOf(discount.getDiscountId()));
				tariff.setValue(route.getPrice().multiply(new BigDecimal(discount.getValue()).divide(new BigDecimal(100))));
				tariff.setName(discount.getName());
				if (discount.getRelation() != null) {
					tariff.setDescription(discount.getRelation().getComment());
				}
			});
		}
		return tariffs;
	}

	//*** BILL/PAY/CANCEL
	private Response[] getResponse(RequestEntity<?> requestEntity) throws ResponseError {
		return template.exchange(requestEntity, Response[].class).getBody();
	}

	public void reserve(ServiceItem service, Customer customer) throws ResponseError {
		NewOrderRequest newOrderRequest = new NewOrderRequest();
		newOrderRequest.setRouteId(service.getId());
		newOrderRequest.setPassengers(Arrays.asList(getServicePassenger(service, customer)));
		try {
			NewOrderResponse newOrderResponse = getResult(template,
					getRequestEntity(newOrderRequest, HttpMethod.POST, BOOKING_NEW_ORDER_URI), NewOrderResponse.class);
			if (newOrderResponse.getOrders() != null && !newOrderResponse.getOrders().isEmpty()) {
				service.setExpire(newOrderResponse.getOrders().get(0).getPayNoLaterThan());
				return;
			}
			throw new Exception("Empty response");
		} catch (Exception e) {
			service.setConfirmed(false);
			service.setError(new RestError(e.getMessage()));
			LOGGER.error(e.getMessage(), e);
		}
	}

	private Passenger getServicePassenger(ServiceItem serviceItem, Customer customer) {
		Passenger passenger = new Passenger();
		passenger.setFirstname(customer.getName());
		passenger.setLastname(customer.getSurname());
		passenger.setEmail(customer.getEmail());
		passenger.setPhone(customer.getPhone());
		passenger.setRelations(getRelations(customer));
		if (serviceItem.getSeat() != null && serviceItem.getSeat().getId() != null
				&& !serviceItem.getSeat().getId().isEmpty()) {
			SeatIdModel seat = (SeatIdModel) new SeatIdModel().create(serviceItem.getSeat().getId());
			passenger.setSeat(String.valueOf(seat.getSeatNum()));
			passenger.setSeatId(String.valueOf(seat.getSeatId()));
			passenger.setTransportId(String.valueOf(seat.getTransportId()));
		}
		if (serviceItem.getPrice() != null && serviceItem.getPrice().getTariff() != null && serviceItem.getPrice().getTariff().getId() != null) {
			passenger.setDiscount(new Discount(Integer.valueOf(serviceItem.getPrice().getTariff().getId())));
		}
		return passenger;
	}

	private Relations getRelations(Customer customer) {
		Relations relations = new Relations();
		relations.setBirth(customer.getBirthday());
		if (customer.getCitizenship() != null) {
			relations.setCitizenship(customer.getCitizenship().toString());
		}
		if (customer.getGender() != null) {
			relations.setGender(customer.getGender().toString());
		}
		relations.setDocType(getDocType(customer.getDocumentType()));
		if (customer.getDocumentSeries() != null && customer.getDocumentNumber() != null) {
			relations.setDocNumber(customer.getDocumentSeries() + customer.getDocumentNumber());
		}
		return relations;
	}

	private Integer getDocType(com.gillsoft.model.IdentificationDocumentType documentType) {
		if (documentType != null) {
			switch (documentType) {
			case FOREIGN_PASSPORT:
				return 1;
			case PASSPORT:
				return 2;
			case BIRTH_CERTIFICATE:
				return 3;
			// case STUDENT_CARD:
			// return 4;
			default:
				return null;
			}
		}
		return null;
	}

	public void pay(String orderId) {
		
	}

	/*public Response booking(Response reserveResponse) throws ResponseError {
		BookingRequest bookingRequest = new BookingRequest(getTicket(reserveResponse));
		Response[] response = getResponse(getRequestEntity(bookingRequest, HttpMethod.PUT, TICKETS + '/' + reserveResponse.getTicketId()));
		if (response != null && response.length != 0) {
			if (response[0].getStatus() != null && response[0].getStatus().getCode() != null && response[0].getStatus().getCode() != 0) {
				throw new ResponseError(response[0].getStatus().getMessage());
			}
			return response[0];
		}
		throw new ResponseError("Response error");
	}*/

	/*private Ticket getTicket(Response reserveResponse) {
		Ticket ticket = new Ticket();
		ticket.setPlace(reserveResponse.getPlaceNumber());
		ticket.setTicketId(String.valueOf(reserveResponse.getTicketId()));
		ticket.setFirstName(reserveResponse.getCustomer().getName());
		ticket.setLastName(reserveResponse.getCustomer().getSurname());
		ticket.setThirdName(reserveResponse.getCustomer().getPatronymic() != null ? reserveResponse.getCustomer().getPatronymic() : "NA");
		ticket.setFirstNameLatin(reserveResponse.getCustomer().getName());
		ticket.setLastNameLatin(reserveResponse.getCustomer().getSurname());
		ticket.setPhone(reserveResponse.getCustomer().getPhone());
		ticket.setEmail(reserveResponse.getCustomer().getEmail());
		ticket.setGender(String.valueOf(reserveResponse.getCustomer().getGender()).toLowerCase());
		ticket.setBirthDate(reserveResponse.getCustomer().getBirthday());
		ticket.setDocNumber(reserveResponse.getCustomer().getDocumentSeries() != null
				&& reserveResponse.getCustomer().getDocumentNumber() != null
						? reserveResponse.getCustomer().getDocumentSeries()
								+ reserveResponse.getCustomer().getDocumentNumber()
						: null);
		ticket.setDocType(getDocTypeCode(reserveResponse.getCustomer().getDocumentType()));
		ticket.setCitizenship(reserveResponse.getCustomer().getCitizenship() != null
				? reserveResponse.getCustomer().getCitizenship().getAlfa3Code() : null);
		ticket.setBasePrice(String.valueOf(reserveResponse.getPrice().getPrice()));
		ticket.setPrice(String.valueOf(reserveResponse.getPrice().getPrice()));
		ticket.setCurrency(reserveResponse.getPrice().getCurrency());
		return ticket;
		return null;
	}*/

	/*public CancelResponse cancel(String ticketId) throws ResponseError {
		return getResult(searchTemplate, null, TICKETS + '/' + ticketId, HttpMethod.DELETE, CancelResponse.getTypeReference());
	}*/

	/*************************************************/
	/*private <T> T getResult(RestTemplate template, Request request, String method, HttpMethod httpMethod,
			ParameterizedTypeReference<T> type) throws ResponseError {
		URI uri = UriComponentsBuilder.fromUriString(Config.getUrl() + method).build().toUri();
		RequestEntity<Request> requestEntity = new RequestEntity<>(request, headers, httpMethod, uri);
		ResponseEntity<T> response = template.exchange(requestEntity, type);
		return response.getBody();
		return null; //getResult(template, request, method, httpMethod, type, null);
	}

	/*private <T> T getResult(RestTemplate template, Request request, String method, HttpMethod httpMethod,
			ParameterizedTypeReference<T> type, String lang) throws ResponseError {
		if (lang != null) {
			headers.set("Accept-Language", lang);
			headers.set("Content-Language", lang);
		}
		URI uri = UriComponentsBuilder.fromUriString(Config.getUrl() + method).build().toUri();
		RequestEntity<Request> requestEntity = new RequestEntity<>(request, headers, httpMethod, uri);
		ResponseEntity<T> response = template.exchange(requestEntity, type);
		return response.getBody();
	}*/

	/*private <T> T getResult(RestTemplate template, Request request, MultiValueMap<String, String> body, String method, HttpMethod httpMethod,
			Class<T> clazz, String lang) throws ResponseError {
		if (lang != null) {
			headers.set("Accept-Language", lang);
			headers.set("Content-Language", lang);
		}
		HttpEntity<MultiValueMap<String, String>> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
		URI uri = UriComponentsBuilder.fromUriString(Config.getUrl() + method).build().toUri();
		RequestEntity<Request> requestEntity = new RequestEntity<>(request, headers, httpMethod, uri);
		ResponseEntity<Response<T>> response = template.exchange(requestEntity, new ParameterizedTypeReference<Response<T>>() { });
		if (response == null || response.getBody() == null || response.getBody().getData() == null) {
			throw new ResponseError("Response body is empty");
		}
		if (response.getBody().getData().isError()) {
			if (response.getBody().getData().getErrorMsg() != null) {
				throw new ResponseError(response.getBody().getData().getErrorMsg());
			}
			if (response.getBody().getData().getErrorMsgList() != null) {
				throw new ResponseError(String.valueOf(response.getBody().getData().getErrorMsgList()));
			}
			if (response.getBody().getData().getErrorList() != null) {
				throw new ResponseError(String.join(";", response.getBody().getData().getErrorList().stream()
						.map(com.gillsoft.client.model.booking.Error::toString).collect(Collectors.toList())));
			}
		}
		return response.getBody().getData().getResponse(clazz);
	}

	private <T> RequestEntity<T> getRequestEntity(T request, HttpMethod httpMethod, String method) {
		return new RequestEntity<>(request, headers, httpMethod,
				UriComponentsBuilder.fromUriString(Config.getUrl() + method).build().toUri());
	}*/

	private <T> RequestEntity<T> getRequestEntity(T request, HttpMethod httpMethod, URI uri) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set(HttpHeaders.AUTHORIZATION, auth.getToken());
		requestHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new RequestEntity<>(request, requestHeaders, httpMethod, uri);
	}

	private <T, U> T getResult(RestTemplate template, RequestEntity<U> request, Class<T> type) throws ResponseError {
		Response<T> response = template.exchange(request, new ParameterizedTypeReference<Response<T>>() {
		}).getBody();
		checkResponseError(response);
		return response.getData().getResponse(type);
	}

	private <T> T getResult(RestTemplate template, HttpEntity<MultiValueMap<String, String>> request, Class<T> type,
			HttpMethod method, URI uri) throws ResponseError {
		if (type.equals(Auth.class)) {
			return template.exchange(uri, method, request, type).getBody();
		}
		Response<T> response = template.exchange(uri, method, request, new ParameterizedTypeReference<Response<T>>() {
		}).getBody();
		checkResponseError(response);
		return response.getData().getResponse(type);
	}

	private <T> T checkResponseError(Response<T> response) throws ResponseError {
		if (response == null || response.getData() == null) {
			throw new ResponseError("Response body is empty");
		}
		if (response.getData().isError()) {
			if (response.getData().getErrorMsg() != null) {
				throw new ResponseError(response.getData().getErrorMsg());
			}
			if (response.getData().getErrorMsgList() != null) {
				throw new ResponseError(String.valueOf(response.getData().getErrorMsgList()));
			}
			if (response.getData().getErrorList() != null) {
				throw new ResponseError(String.join(";", response.getData().getErrorList().stream()
						.map(com.gillsoft.client.model.booking.Error::toString).collect(Collectors.toList())));
			}
		}
		return null;
	}

	public CacheHandler getCache() {
		return cache;
	}

	public static RestClientException createUnavailableMethod() {
		return new RestClientException("Method is unavailable");
	}

}
