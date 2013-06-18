package library.seat.manage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import play.db.DB;

import library.seat.manage.dao.BaseDao;
import library.seat.manage.dao.IOrderDao;
import library.seat.manage.dto.OrdersInfo;
import library.seat.manage.dto.PageInfo;
import library.seat.manage.dto.UserInfo;
import library.seat.manage.exception.DataAccessException;
import library.seat.manage.util.FieldValueCriteria;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
public class OrderDaoImpl extends BaseDao implements IOrderDao {
	
	private static final String TABLE = "ORDERS"; 

	@Override
	public int add(OrdersInfo order) throws DataAccessException {
		String insertSql = "INSERT INTO ORDERS(USER_ID, DESK_ID, SEAT_NUM, RESERVE_TYPE, RESERVE_BEGIN_TIME, " +
				"RESERVE_END_TIME) VALUES(?, ?, ?, ?, ?, ?)";
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		//ResultSet rs = null;
		
		try {
		    statement = conn.prepareStatement(insertSql);
		    statement.setString(1, "" + order.getUserId());
		    statement.setString(2, "" + order.getDeskId());
		    statement.setInt(3, order.getSeatNum());
		    statement.setString(4, order.getReserveType());
		    statement.setTimestamp(5, order.getReserveBeginTime());
		    statement.setTimestamp(6, order.getReserveEndTime());
		    statement.execute();
		    
		    /*
		    String lastInsertId = "SELECT LAST_INSERT_ID() AS ID";
		    rs = conn.prepareStatement(lastInsertId).executeQuery();
		    if (rs.next()) {
		        rs.absolute(1);
		        int id = rs.getInt("ID");
	            return id;
		    }
		    */
		    
		} catch (SQLException e) {
			throw new DataAccessException("error when insert record", e);
		} finally {
		    if (statement != null) {
		        try {
		            statement.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
	    return 0;
	}

	@Override
	public void delete(int orderId) throws DataAccessException {
		
	}

	@Override
	public int getCount(List<FieldValueCriteria> criteria)
			throws DataAccessException {
		StringBuilder queryRecs = new StringBuilder("select count(*) from " + TABLE + " where 1 = 1");
		appendCriterias(criteria, queryRecs);
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(queryRecs.toString());
		    for(int i=0; i<criteria.size(); i++) {
		    	statement.setObject(i+1, criteria.get(i).getFieldValue());
		    }
		    rs = statement.executeQuery();
		    if(rs.next()) {
		    	return rs.getInt(1);
		    }	
		} catch (SQLException e) {
			 throw new DataAccessException("error when get record totalCount", e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
			    } catch (SQLException e) {
			    	e.printStackTrace();
			    }
			}
		}
		return 0;
	}

	@Override
	public List<OrdersInfo> findByCriteria(List<FieldValueCriteria> criteria)
			throws DataAccessException {
		StringBuilder queryRecs = new StringBuilder("select * from " + TABLE + " where 1 = 1");
		appendCriterias(criteria, queryRecs);
		
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<OrdersInfo> orderList = new ArrayList<OrdersInfo>();
		try {
			statement = conn.prepareStatement(queryRecs.toString());
		    for(int i=0; i<criteria.size(); i++) {
		    	statement.setObject(i+1, criteria.get(i).getFieldValue());
		    }
		    rs = statement.executeQuery();
		    while(rs.next()) {
		    	OrdersInfo order = new OrdersInfo();
		    	order.setId(rs.getInt("ID"));
		    	order.setDeskId(rs.getInt("DESK_ID"));
		    	order.setUserId(rs.getInt("USER_ID"));
		    	order.setSeatNum(rs.getInt("SEAT_NUM"));
		    	order.setReserveType(rs.getString("RESERVE_TYPE"));
		    	order.setReserveBeginTime(rs.getTimestamp("RESERVE_BEGIN_TIME"));
		    	order.setReserveEndTime(rs.getTimestamp("RESERVE_END_TIME"));
		    	orderList.add(order);
		    }
		    return orderList;
		 } catch (SQLException e) {
			 throw new DataAccessException("error when search record", e);
		 } finally {
			 if (statement != null) {
				 try {
					 statement.close();
			     } catch (SQLException e) {
			    	 e.printStackTrace();
			     }
			}
		 }
	}

	@Override
	public PageInfo<OrdersInfo> findByCriteria(
			List<FieldValueCriteria> criteria, PageInfo pageInfo)
			throws DataAccessException {
		StringBuilder queryRecs = new StringBuilder("select * from " + TABLE + " where 1 = 1");
		appendCriterias(criteria, queryRecs);
		if(pageInfo.getOrderBy() != null){
			queryRecs.append(" order by " + pageInfo.getOrderBy() + " " + pageInfo.getOrder());
		}else{
			queryRecs.append(" order by reserve_begin_time desc "); 
			pageInfo.setOrderBy("reserve_begin_time");
			pageInfo.setOrder("desc");
		}
		queryRecs.append(" limit " + pageInfo.getFirst() + " , "
				+ pageInfo.getPageSize());
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(queryRecs.toString());
		    for(int i=0; i<criteria.size(); i++) {
		    	statement.setObject(i+1, criteria.get(i).getFieldValue());
		    }
		    rs = statement.executeQuery();
		    while(rs.next()) {
		    	OrdersInfo order = new OrdersInfo();
		    	order.setId(rs.getInt("ID"));
		    	order.setUserId(Integer.parseInt(rs.getString("USER_ID")));
		    	order.setDeskId(Integer.parseInt(rs.getString("DESK_ID")));
		    	order.setSeatNum(rs.getInt("SEAT_NUM"));
		    	order.setReserveType(rs.getString("RESERVE_TYPE"));
		    	order.setReserveBeginTime(rs.getTimestamp("RESERVE_BEGIN_TIME"));
		    	order.setReserveEndTime(rs.getTimestamp("RESERVE_END_TIME"));
		    	pageInfo.getResult().add(order);
		    }
		    return pageInfo;
		} catch (SQLException e) {
			 throw new DataAccessException("error when search record", e);
		} finally {
		    if (statement != null) {
		        try {
		            statement.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
	}

}
