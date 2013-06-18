package library.seat.manage.dto;



/**
 * user实体类
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
public class UserInfo extends ExtFieldDto {
	
	/**
	 * id
	 */
	private int userId;
	
	/**
	 * 用户标识ID
	 */
	private String userNum;
	
	/**
	 * 用户密码
	 */
	private String password;
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 用户所属学院
	 */
	private String dept;
	
	/**
	 * 用户预约次数
	 */
	private int reserveTimes;
	
	/**
	 * 用户失约次数
	 */
	private int breakTimes;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getReserveTimes() {
		return reserveTimes;
	}

	public void setReserveTimes(int reserveTimes) {
		this.reserveTimes = reserveTimes;
	}

	public int getBreakTimes() {
		return breakTimes;
	}

	public void setBreakTimes(int breakTimes) {
		this.breakTimes = breakTimes;
	}

	
	
	
}
