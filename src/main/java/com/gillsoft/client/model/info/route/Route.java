package com.gillsoft.client.model.info.route;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gillsoft.LocalityServiceController;
import com.gillsoft.client.model.info.details.TripDetails;
import com.gillsoft.client.model.info.route.discounts.Discount;
import com.gillsoft.client.model.info.seats.FreeSeat;
import com.gillsoft.client.model.info.ticket.Calculation;
import com.gillsoft.model.CalcType;
import com.gillsoft.model.Commission;
import com.gillsoft.model.Currency;
import com.gillsoft.model.Locality;
import com.gillsoft.model.Organisation;
import com.gillsoft.model.Price;
import com.gillsoft.model.Segment;
import com.gillsoft.model.Tariff;
import com.gillsoft.model.Trip;
import com.gillsoft.model.ValueType;
import com.gillsoft.model.Vehicle;
import com.gillsoft.util.StringUtil;

public class Route implements Serializable {

	private static final long serialVersionUID = 1711839622406782569L;

	@JsonProperty("route_name")
    private String routeName;

    private String system;

    private BigDecimal price;

    @JsonProperty("reservation_is_possible")
    private Boolean reservationIsPossible;

    @JsonProperty("reservation_time")
    private Integer reservationTime;

    @JsonProperty("sale_is_stopped_for")
    private Integer saleIsStoppedFor;

    @JsonProperty("route_id")
    private String routeId;

    @JsonProperty("route_number")
    private String routeNumber;

    private String currency;

    @JsonProperty("currency_name")
    private String currencyName;

    @JsonProperty("from_city_name")
    private String fromCityName;

    @JsonProperty("from_station_id")
    private Integer fromStationId;

    @JsonProperty("from_station")
    private String fromStation;

    @JsonProperty("from_station_coord")
    private String fromStationCoord;

    @JsonProperty("to_city_name")
    private String toCityName;

    @JsonProperty("to_station_id")
    private Integer toStationId;

    @JsonProperty("to_station")
    private String toStation;

    @JsonProperty("to_station_coord")
    private String toStationCoord;

    @JsonProperty("free_choice_of_places")
    private Boolean freeChoiceOfPlaces;

    @JsonProperty("datetime_departure")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetimeDeparture;

    @JsonProperty("datetime_arrival")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetimeArrival;

    @JsonProperty("date_departure")
    private String dateDeparture;

    @JsonProperty("time_departure")
    private String timeDeparture;

    @JsonProperty("date_arrival")
    private String dateArrival;

    @JsonProperty("time_arrival")
    private String timeArrival;

    @JsonProperty("vip_route")
    private Boolean vipRoute;

    @JsonProperty("transport_pic")
    private List<String> transportPic;

    @JsonProperty("agent_profit")
    private BigDecimal agentProfit;

    @JsonProperty("agent_commission_rate")
    private BigDecimal agentCommissionRate;

    @JsonProperty("route_comment")
    private String routeComment;

    @JsonProperty("carrier_name")
    private String carrierName;

    @JsonProperty("carrier_addr")
    private String carrierAddr;

    @JsonProperty("carrier_phone")
    private String carrierPhone;

    @JsonProperty("required_ticket_fields")
    private List<RequiredTicketField> requiredTicketFields;

    @JsonProperty("passenger_name_format")
    private String passengerNameFormat;

    private Time travel;

    private List<Transfer> change;

    @JsonProperty("free_seats")
    private Integer freeSeats;

    @JsonProperty("trip_options")
    private List<TripOption> tripOptions;

    @JsonProperty("carrier_rating")
    private Byte carrierRating;

    private TripDetails tripDetails;

    private List<FreeSeat> freeSeatsList;

    private List<Discount> routeDiscounts;

    private Calculation ticketPrice;

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getReservationIsPossible() {
		return reservationIsPossible;
	}

	public void setReservationIsPossible(Boolean reservationIsPossible) {
		this.reservationIsPossible = reservationIsPossible;
	}

	public Integer getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(Integer reservationTime) {
		this.reservationTime = reservationTime;
	}

	public Integer getSaleIsStoppedFor() {
		return saleIsStoppedFor;
	}

	public void setSaleIsStoppedFor(Integer saleIsStoppedFor) {
		this.saleIsStoppedFor = saleIsStoppedFor;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getFromCityName() {
		return fromCityName;
	}

	public void setFromCityName(String fromCityName) {
		this.fromCityName = fromCityName;
	}

	public Integer getFromStationId() {
		return fromStationId;
	}

	public void setFromStationId(Integer fromStationId) {
		this.fromStationId = fromStationId;
	}

	public String getFromStation() {
		return fromStation;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public String getFromStationCoord() {
		return fromStationCoord;
	}

	public void setFromStationCoord(String fromStationCoord) {
		this.fromStationCoord = fromStationCoord;
	}

	public String getToCityName() {
		return toCityName;
	}

	public void setToCityName(String toCityName) {
		this.toCityName = toCityName;
	}

	public Integer getToStationId() {
		return toStationId;
	}

	public void setToStationId(Integer toStationId) {
		this.toStationId = toStationId;
	}

	public String getToStation() {
		return toStation;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	public String getToStationCoord() {
		return toStationCoord;
	}

	public void setToStationCoord(String toStationCoord) {
		this.toStationCoord = toStationCoord;
	}

	public Boolean getFreeChoiceOfPlaces() {
		return freeChoiceOfPlaces;
	}

	public void setFreeChoiceOfPlaces(Boolean freeChoiceOfPlaces) {
		this.freeChoiceOfPlaces = freeChoiceOfPlaces;
	}

	public Date getDatetimeDeparture() {
		return datetimeDeparture;
	}

	public void setDatetimeDeparture(Date datetimeDeparture) {
		this.datetimeDeparture = datetimeDeparture;
	}

	public Date getDatetimeArrival() {
		return datetimeArrival;
	}

	public void setDatetimeArrival(Date datetimeArrival) {
		this.datetimeArrival = datetimeArrival;
	}

	public String getDateDeparture() {
		return dateDeparture;
	}

	public void setDateDeparture(String dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public String getTimeDeparture() {
		return timeDeparture;
	}

	public void setTimeDeparture(String timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	public String getDateArrival() {
		return dateArrival;
	}

	public void setDateArrival(String dateArrival) {
		this.dateArrival = dateArrival;
	}

	public String getTimeArrival() {
		return timeArrival;
	}

	public void setTimeArrival(String timeArrival) {
		this.timeArrival = timeArrival;
	}

	public Boolean getVipRoute() {
		return vipRoute;
	}

	public void setVipRoute(Boolean vipRoute) {
		this.vipRoute = vipRoute;
	}

	public List<String> getTransportPic() {
		return transportPic;
	}

	public void setTransportPic(List<String> transportPic) {
		this.transportPic = transportPic;
	}

	public BigDecimal getAgentProfit() {
		return agentProfit;
	}

	public void setAgentProfit(BigDecimal agentProfit) {
		this.agentProfit = agentProfit;
	}

	public BigDecimal getAgentCommissionRate() {
		return agentCommissionRate;
	}

	public void setAgentCommissionRate(BigDecimal agentCommissionRate) {
		this.agentCommissionRate = agentCommissionRate;
	}

	public String getRouteComment() {
		return routeComment;
	}

	public void setRouteComment(String routeComment) {
		this.routeComment = routeComment;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getCarrierAddr() {
		return carrierAddr;
	}

	public void setCarrierAddr(String carrierAddr) {
		this.carrierAddr = carrierAddr;
	}

	public String getCarrierPhone() {
		return carrierPhone;
	}

	public void setCarrierPhone(String carrierPhone) {
		this.carrierPhone = carrierPhone;
	}

	public List<RequiredTicketField> getRequiredTicketFields() {
		return requiredTicketFields;
	}

	public void setRequiredTicketFields(List<RequiredTicketField> requiredTicketFields) {
		this.requiredTicketFields = requiredTicketFields;
	}

	public String getPassengerNameFormat() {
		return passengerNameFormat;
	}

	public void setPassengerNameFormat(String passengerNameFormat) {
		this.passengerNameFormat = passengerNameFormat;
	}

	public Time getTravel() {
		return travel;
	}

	public void setTravel(Time travel) {
		this.travel = travel;
	}

	public List<Transfer> getChange() {
		return change;
	}

	public void setChange(List<Transfer> change) {
		this.change = change;
	}

	public Integer getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(Integer freeSeats) {
		this.freeSeats = freeSeats;
	}

	public List<TripOption> getTripOptions() {
		return tripOptions;
	}

	public void setTripOptions(List<TripOption> tripOptions) {
		this.tripOptions = tripOptions;
	}

	public Byte getCarrierRating() {
		return carrierRating;
	}

	public void setCarrierRating(Byte carrierRating) {
		this.carrierRating = carrierRating;
	}

	public TripDetails getTripDetails() {
		return tripDetails;
	}

	public void setTripDetails(TripDetails tripDetails) {
		this.tripDetails = tripDetails;
	}

	public List<FreeSeat> getFreeSeatsList() {
		return freeSeatsList;
	}

	public void setFreeSeatsList(List<FreeSeat> freeSeatsList) {
		this.freeSeatsList = freeSeatsList;
	}

	public List<Discount> getRouteDiscounts() {
		return routeDiscounts;
	}

	public void setRouteDiscounts(List<Discount> routeDiscounts) {
		this.routeDiscounts = routeDiscounts;
	}

	public Calculation getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Calculation ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Trip getTrip() {
		Trip trip = new Trip();
		trip.setId(this.getRouteId());
		//trip.setSegments(Arrays.asList(getSegment(trip.getId())));
		return trip;
	}

	public Segment getSegment() {
		Segment segment = new Segment(this.routeId);
		segment.setNumber(this.routeNumber != null && !this.routeNumber.isEmpty() ? this.routeNumber : this.routeName);
		segment.setDeparture(getDepartureLocation());
		if (segment.getDeparture() != null) {
			segment.getDeparture().setParent(LocalityServiceController.getLocality(this.fromCityName));
		}
		segment.setDepartureDate(this.getDatetimeDeparture());
		segment.setArrival(getArrivalLocation());
		if (segment.getArrival() != null) {
			segment.getArrival().setParent(LocalityServiceController.getLocality(this.toCityName));
		}
		segment.setArrivalDate(this.getDatetimeArrival());
		segment.setFreeSeatsCount(this.freeSeats);
		if (this.carrierName != null && !this.carrierName.isEmpty()) {
			segment.setCarrier(getCarrier());
		}
		if (this.freeSeatsList != null && !this.freeSeatsList.isEmpty()) {
			FreeSeat freeSeat = this.freeSeatsList.get(0);
			segment.setVehicle(new Vehicle(String.valueOf(freeSeat.getTransportId())));
			segment.getVehicle().setCapacity(freeSeat.getSeats());
			segment.getVehicle().setModel(freeSeat.getDescription());
		}
		/*if (this.requiredTicketFields != null && !this.requiredTicketFields.isEmpty()) {
			segment.setRequired(getRequired());
		}*/
		segment.setPrice(getSegmentPrice());
		return segment;
	}

	private Locality getDepartureLocation() {
		Locality locality = new Locality(String.valueOf(this.fromStationId));
		locality.setName(this.fromStation);
		return locality;
	}

	private Locality getArrivalLocation() {
		Locality locality = new Locality(String.valueOf(this.toStationId));
		locality.setName(this.toStation);
		return locality;
	}

	private Organisation getCarrier() {
		Organisation carrier = new Organisation(StringUtil.md5(this.carrierName));
		carrier.setName(this.carrierName);
		if (this.carrierAddr != null && !this.carrierAddr.isEmpty()) {
			carrier.setAddress(this.carrierAddr);
		}
		if (this.carrierPhone != null && !this.carrierPhone.isEmpty()) {
			carrier.setPhones(Arrays.asList(this.carrierPhone));
		}
		return carrier;
	}

	private Price getSegmentPrice() {
		Price segmentPrice = new Price();
		segmentPrice.setAmount(this.getPrice());
		segmentPrice.setCurrency(Currency.valueOf(this.currency));
		segmentPrice.setTariff(new Tariff());
		if (this.agentCommissionRate != null) {
			segmentPrice.setCommissions(new ArrayList<>());
			Commission agentCommission = new Commission();
			segmentPrice.getCommissions().add(agentCommission);
			agentCommission.setCode("AGENT_PERCENT");
			agentCommission.setType(ValueType.PERCENT);
			agentCommission.setValueCalcType(CalcType.IN);
			agentCommission.setCurrency(segmentPrice.getCurrency());
			agentCommission.setValue(this.agentCommissionRate);
		} else if (this.agentProfit != null) {
			segmentPrice.setCommissions(new ArrayList<>());
			Commission agentCommission = new Commission();
			segmentPrice.getCommissions().add(agentCommission);
			agentCommission.setCode("AGENT_FIXED");
			agentCommission.setType(ValueType.FIXED);
			agentCommission.setValueCalcType(CalcType.IN);
			agentCommission.setCurrency(segmentPrice.getCurrency());
			agentCommission.setValue(this.agentProfit);
		}
		return segmentPrice;
	}
	
/*
				RoutePrice routePrice = getCarrierPrice(route.getPrice());
				Trip tmpTrip = new Trip();
				tmpTrip.setId(new TripIdModel(route.getId(), tripPackage.getFrom(), tripPackage.getTo(),
						route.getDispatchDateAsString(), route.getArrivalDateAsString(), routePrice).asString());
				trips.add(tmpTrip);

				String segmentId = tmpTrip.getId();
				Segment segment = segments.get(segmentId);
				if (segment == null) {
					segment = new Segment();
					segment.setId(segmentId);
					segment.setNumber(route.getName());
					try {
						segment.setDepartureDate(route.getDispatchDate());
						segment.setArrivalDate(route.getArrivalDate());
					} catch (Exception e) {}
					segment.setFreeSeatsCount(route.getFreeSeatCount());
					segments.put(segmentId, segment);
				}
				segment.setDeparture(addStation(localities, tripPackage.getFrom()));
				segment.setArrival(addStation(localities, tripPackage.getTo()));
				segment.setPrice(getPrice(routePrice));
*/


}
