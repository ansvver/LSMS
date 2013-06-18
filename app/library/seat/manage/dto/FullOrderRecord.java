package library.seat.manage.dto;

import java.sql.Timestamp;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 23:50:00
 */
public class FullOrderRecord extends ExtFieldDto {

	private int orderId;
	private String userNum;
	private String name;
	private String dept;
	private String seatInfo;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getSeatInfo() {
		return seatInfo;
	}
	public void setSeatInfo(String seatInfo) {
		this.seatInfo = seatInfo;
	}

}
