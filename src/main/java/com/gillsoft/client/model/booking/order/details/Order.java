package com.gillsoft.client.model.booking.order.details;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order implements Serializable {

	private static final long serialVersionUID = 633762307067588483L;

	@JsonProperty("order_id")
    private Integer orderId;

	@JsonProperty("order_num")
    private String orderNum;

	@JsonProperty("from_station_id")
    private Integer fromStationId;

	@JsonProperty("to_station_id")
    private Integer toStationId;

	@JsonProperty("order_status")
    private String orderStatus;

	@JsonProperty("order_datetime")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDatetime;

	@JsonProperty("status_datetime")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date statusDatetime;

	@JsonProperty("departure_datetime")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departureDatetime;

	@JsonProperty("arrival_datetime")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date arrivalDatetime;

	@JsonProperty("route_name")
	private String routeName;

	@JsonProperty("route_number")
    private String routeNumber;

	@JsonProperty("from_city_name")
    private String fromCityName;

	@JsonProperty("to_city_name")
    private String toCityName;

	@JsonProperty("from_station_coord")
    private String fromStationCoord;

	@JsonProperty("to_station_coord")
    private String toStationCoord;

    private String currency;

    @JsonProperty("only_original_tickets")
    private Boolean onlyOriginalTickets;

    @JsonProperty("tickets_download_link")
	private String ticketsDownloadLink;

    @JsonProperty("pay_no_later_than")
    private Date payNoLaterThan;

    private String system;

    private BigDecimal sum;

    @JsonProperty("agent_profit")
    private BigDecimal agentProfit;

    @JsonProperty("agent_commission_rate")
    private BigDecimal agentCommissionRate;

    private List<Ticket> tickets;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getFromStationId() {
		return fromStationId;
	}

	public void setFromStationId(Integer fromStationId) {
		this.fromStationId = fromStationId;
	}

	public Integer getToStationId() {
		return toStationId;
	}

	public void setToStationId(Integer toStationId) {
		this.toStationId = toStationId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(Date orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
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

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
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

	public String getFromStationCoord() {
		return fromStationCoord;
	}

	public void setFromStationCoord(String fromStationCoord) {
		this.fromStationCoord = fromStationCoord;
	}

	public String getToStationCoord() {
		return toStationCoord;
	}

	public void setToStationCoord(String toStationCoord) {
		this.toStationCoord = toStationCoord;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Boolean getOnlyOriginalTickets() {
		return onlyOriginalTickets;
	}

	public void setOnlyOriginalTickets(Boolean onlyOriginalTickets) {
		this.onlyOriginalTickets = onlyOriginalTickets;
	}

	public String getTicketsDownloadLink() {
		return ticketsDownloadLink;
	}

	public void setTicketsDownloadLink(String ticketsDownloadLink) {
		this.ticketsDownloadLink = ticketsDownloadLink;
	}

	public Date getPayNoLaterThan() {
		return payNoLaterThan;
	}

	public void setPayNoLaterThan(Date payNoLaterThan) {
		this.payNoLaterThan = payNoLaterThan;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
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

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
