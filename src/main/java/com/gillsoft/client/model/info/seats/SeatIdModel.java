package com.gillsoft.client.model.info.seats;

import com.gillsoft.model.AbstractJsonModel;

public class SeatIdModel extends AbstractJsonModel {

	private static final long serialVersionUID = -1737862172974532861L;

	private Integer seatId;

	private Integer seatNum;

	private Integer transportId;

	public SeatIdModel() {
		
	}

	public SeatIdModel(Integer seatId, Integer seatNum, Integer transportId) {
		this.seatId = seatId;
		this.seatNum = seatNum;
		this.transportId = transportId;
	}

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	

}
