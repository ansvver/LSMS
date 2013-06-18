package library.seat.manage.dto;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
public class DeskInfo extends ExtFieldDto {
	
	private int deskId;
	
	private int floor;
	
	private String block;
	
	private int deskNum;
	
	private Boolean isAble;
	
	public int getDeskId() {
		return deskId;
	}

	public void setDeskId(int deskId) {
		this.deskId = deskId;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public int getDeskNum() {
		return deskNum;
	}

	public void setDeskNum(int deskNum) {
		this.deskNum = deskNum;
	}

	public Boolean getIsAble() {
		return isAble;
	}

	public void setIsAble(Boolean isAble) {
		this.isAble = isAble;
	}
	
}
