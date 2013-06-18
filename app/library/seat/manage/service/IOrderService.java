package library.seat.manage.service;

import java.sql.Timestamp;
import java.util.List;

import library.seat.manage.dto.OrdersInfo;
import library.seat.manage.dto.PageInfo;
import library.seat.manage.exception.BusinessException;
import library.seat.manage.exception.DataAccessException;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 2-1-2013 13:20:00
 */
public interface IOrderService {
	
	
	PageInfo<OrdersInfo> queryByConditon(int deskId, int userId, Timestamp reserveBeginTime, 
			Timestamp reserveEndTime, PageInfo pageInfo);
	
	List<OrdersInfo> queryOrders(int deskId, int userId, Timestamp reserveBeginTime, 
			Timestamp reserveEndTime);
	
	OrdersInfo getOrderById(int orderId);

	void order(OrdersInfo order) throws BusinessException;
	
	
}
