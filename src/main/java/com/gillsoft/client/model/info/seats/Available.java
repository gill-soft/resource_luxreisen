package com.gillsoft.client.model.info.seats;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gillsoft.model.Seat;
import com.gillsoft.model.SeatStatus;
import com.gillsoft.model.SeatType;

public class Available implements Serializable {

	private static final long serialVersionUID = -4578210624099776613L;

	@JsonProperty("seat_num")
	private Integer seatNum;

	@JsonProperty("seat_id")
    private Integer seatId;

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Seat toSeat(Integer transportId) {
		Seat seat = new Seat();
		seat.setId(new SeatIdModel(this.seatId, this.seatNum, transportId).asString());
		seat.setNumber(String.valueOf(this.seatNum));
		seat.setStatus(SeatStatus.FREE);
		seat.setType(SeatType.SEAT);
		return seat;
	}

}
