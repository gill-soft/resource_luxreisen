package com.gillsoft.client.model.booking.order.details;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 5556344937925898900L;

	@JsonProperty("ticket_id")
    private Integer ticketId;

	@JsonProperty("ticket_number")
    private String ticketNumber;

	@JsonProperty("ticket_status")
    private String ticketStatus;

	@JsonProperty("ticket_status_date")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ticketStatusDate;

	@JsonProperty("free_choice")
    private Boolean freeChoice;

	@JsonProperty("transport_id")
    private Integer transportId;

	@JsonProperty("route_number")
    private String routeNumber;

	@JsonProperty("route_name")
    private String routeName;

    private String carrier;

    @JsonProperty("carrier_phone")
    private String carrierPhone;

    private String insurance;

    @JsonProperty("insurance_desc")
    private String insuranceDesc;

    @JsonProperty("from_city_name")
    private String fromCityName;

    @JsonProperty("to_city_name")
    private String toCityName;

    @JsonProperty("from_station_name")
    private String fromStationName;

    @JsonProperty("to_station_name")
    private String toStationName;

    @JsonProperty("from_station_addr")
    private String fromStationAddr;

    @JsonProperty("to_station_addr")
    private String toStationAddr;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    @JsonProperty("ticket_price")
    private BigDecimal ticketPrice;

    private String currency;

    @JsonProperty("discount_id")
    private Integer discountId;

    @JsonProperty("discount_name")
    private String discountName;

    @JsonProperty("discount_data")
    private String discountData;

    @JsonProperty("discount_price")
    private BigDecimal discountPrice;

    @JsonProperty("price_to_pay")
    private BigDecimal priceToPay;

    @JsonProperty("departure_datetime")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departureDatetime;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("arrival_datetime")
    private Date arrivalDatetime;

    private String comment;

    private List<Relation> relations;

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Date getTicketStatusDate() {
		return ticketStatusDate;
	}

	public void setTicketStatusDate(Date ticketStatusDate) {
		this.ticketStatusDate = ticketStatusDate;
	}

	public Boolean getFreeChoice() {
		return freeChoice;
	}

	public void setFreeChoice(Boolean freeChoice) {
		this.freeChoice = freeChoice;
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCarrierPhone() {
		return carrierPhone;
	}

	public void setCarrierPhone(String carrierPhone) {
		this.carrierPhone = carrierPhone;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getInsuranceDesc() {
		return insuranceDesc;
	}

	public void setInsuranceDesc(String insuranceDesc) {
		this.insuranceDesc = insuranceDesc;
	}

	public String getFromCityName() {
		return fromCityName;
	}

	public void setFromCityName(String fromCityName) {
		this.fromCityName = fromCityName;
	}

	public String getToCityName() {
		return toCityName;
	}

	public void setToCityName(String toCityName) {
		this.toCityName = toCityName;
	}

	public String getFromStationName() {
		return fromStationName;
	}

	public void setFromStationName(String fromStationName) {
		this.fromStationName = fromStationName;
	}

	public String getToStationName() {
		return toStationName;
	}

	public void setToStationName(String toStationName) {
		this.toStationName = toStationName;
	}

	public String getFromStationAddr() {
		return fromStationAddr;
	}

	public void setFromStationAddr(String fromStationAddr) {
		this.fromStationAddr = fromStationAddr;
	}

	public String getToStationAddr() {
		return toStationAddr;
	}

	public void setToStationAddr(String toStationAddr) {
		this.toStationAddr = toStationAddr;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public String getDiscountData() {
		return discountData;
	}

	public void setDiscountData(String discountData) {
		this.discountData = discountData;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getPriceToPay() {
		return priceToPay;
	}

	public void setPriceToPay(BigDecimal priceToPay) {
		this.priceToPay = priceToPay;
	}

	public Date getDepartureDatetime() {
		return departureDatetime;
	}

	public void setDepartureDatetime(Date departureDatetime) {
		this.departureDatetime = departureDatetime;
	}

	public Date getArrivalDatetime() {
		return arrivalDatetime;
	}

	public void setArrivalDatetime(Date arrivalDatetime) {
		this.arrivalDatetime = arrivalDatetime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

}
