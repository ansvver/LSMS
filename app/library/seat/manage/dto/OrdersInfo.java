package library.seat.manage.dto;

import java.sql.Timestamp;
/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 23:50:00
 */
public class OrdersInfo extends ExtFieldDto {
	
	/**
	 * id
	 */
	private int id;
	
	/**
	 * 用户标识id
	 */
	private int userId;
	
	/**
	 * 桌子id
	 */
	private int deskId;
	
	/**
	 * 桌子里的椅子号
	 */
	private int seatNum;
	
	/**
	 * 预订类型
	 */
	private String reserveType;
	
	/**
	 * 预订开始时间
	 */
	private Timestamp reserveBeginTime;
	
	/**
	 * 预订结束时间
	 */
	private Timestamp reserveEndTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDeskId() {
		return deskId;
	}

	public void setDeskId(int deskId) {
		this.deskId = deskId;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public String getReserveType() {
		return reserveType;
	}

	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}

	public Timestamp getReserveBeginTime() {
		return reserveBeginTime;
	}

	public void setReserveBeginTime(Timestamp reserveBeginTime) {
		this.reserveBeginTime = reserveBeginTime;
	}

	public Timestamp getReserveEndTime() {
		return reserveEndTime;
	}

	public void setReserveEndTime(Timestamp reserveEndTime) {
		this.reserveEndTime = reserveEndTime;
	}

	
}
