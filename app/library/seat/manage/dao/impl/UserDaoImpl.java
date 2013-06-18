package library.seat.manage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library.seat.manage.dao.BaseDao;
import library.seat.manage.dao.IUserDao;
import library.seat.manage.dto.PageInfo;
import library.seat.manage.dto.UserInfo;
import library.seat.manage.exception.DataAccessException;
import library.seat.manage.util.FieldValueCriteria;
import library.seat.manage.util.IConditionCriteria;
import play.cache.Cache;
import play.db.DB;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
public class UserDaoImpl extends BaseDao implements IUserDao {
	
	private static final String TABLE = "USER"; 

	@Override
	public int add(UserInfo user) throws DataAccessException{
		String insertSql = "INSERT INTO USER(USER_NUM, PASSWORD, NAME, DEPT, RESERVE_TIMES, " +
				"BREAK_TIMES) VALUES(?, ?, ?, ?, ?, ?)";
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
		    statement = conn.prepareStatement(insertSql);
		    statement.setString(1, user.getUserNum());
		    statement.setString(2, user.getPassword());
		    statement.setString(3, user.getName());
		    statement.setString(4, user.getDept());
		    statement.setInt(5, user.getReserveTimes());
		    statement.setInt(6, user.getBreakTimes());
		    statement.execute();
		    
		    String lastInsertId = "SELECT LAST_INSERT_ID() AS ID";
		    rs = conn.prepareStatement(lastInsertId).executeQuery();
		    if (rs.next()) {
		        rs.absolute(1);
		        int id = rs.getInt("ID");
	            return id;
		    }
		    
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
	public void delete(int id) throws DataAccessException{
		String deleteSql = "DELETE FROM USER WHERE USER_ID = ?";
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
		    statement = conn.prepareStatement(deleteSql);
		    statement.setInt(1, id);
		    statement.execute();
		} catch (SQLException e) {
			throw new DataAccessException("error when delete record", e);
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
	public void update(UserInfo user) throws DataAccessException{
		String updateSql = "UPDATE USER SET USER_NUM = ?, PASSWORD = ?, NAME = ?, DEPT " +
				"= ?, RESERVE_TIMES = ?, BREAK_TIMES = ? WHERE USER_ID = ?";
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
		    statement = conn.prepareStatement(updateSql);
		    statement.setString(1, user.getUserNum());
		    statement.setString(2, user.getPassword());
		    statement.setString(3, user.getName());
		    statement.setString(4, user.getDept());
		    statement.setInt(5, user.getReserveTimes());
		    statement.setInt(6, user.getBreakTimes());
		    statement.setInt(7, user.getUserId());
		    statement.execute();
		} catch (SQLException e) {
			throw new DataAccessException("error when update record", e);
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
	public int getCount(List<FieldValueCriteria> criteria) throws DataAccessException {
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
	public List<UserInfo> findByCriteria(List<FieldValueCriteria> criteria) throws DataAccessException{
		StringBuilder queryRecs = new StringBuilder("select * from " + TABLE + " where 1 = 1");
		appendCriterias(criteria, queryRecs);
		
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<UserInfo> userList = new ArrayList<UserInfo>();
		try {
			statement = conn.prepareStatement(queryRecs.toString());
		    for(int i=0; i<criteria.size(); i++) {
		    	statement.setObject(i+1, criteria.get(i).getFieldValue());
		    }
		    rs = statement.executeQuery();
		    while(rs.next()) {
		    	UserInfo user = new UserInfo();
		    	user.setUserId(rs.getInt("USER_ID"));
		    	user.setUserNum(rs.getString("USER_NUM"));
		    	user.setPassword(rs.getString("PASSWORD"));
		    	user.setName(rs.getString("NAME"));
		    	user.setDept(rs.getString("DEPT"));
		    	user.setReserveTimes(rs.getInt("RESERVE_TIMES"));
		    	user.setBreakTimes(rs.getInt("BREAK_TIMES"));
		    	userList.add(user);
		    }
		    return userList;
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
	public PageInfo<UserInfo> findByCriteria(List<FieldValueCriteria> criteria, PageInfo pageInfo) throws DataAccessException{
		StringBuilder queryRecs = new StringBuilder("select * from " + TABLE + " where 1 = 1");
		appendCriterias(criteria, queryRecs);
		if(pageInfo.getOrderBy() != null){
			queryRecs.append(" order by " + pageInfo.getOrderBy() + " " + pageInfo.getOrder());
		}else{
			queryRecs.append(" order by user_id desc "); 
			pageInfo.setOrderBy("user_id");
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
		    	UserInfo user = new UserInfo();
		    	user.setUserId(rs.getInt("USER_ID"));
		    	user.setUserNum(rs.getString("USER_NUM"));
		    	user.setPassword(rs.getString("PASSWORD"));
		    	user.setName(rs.getString("NAME"));
		    	user.setDept(rs.getString("DEPT"));
		    	user.setReserveTimes(rs.getInt("RESERVE_TIMES"));
		    	user.setBreakTimes(rs.getInt("BREAK_TIMES"));
		    	pageInfo.getResult().add(user);
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

	@Override
	public UserInfo getById(int id) throws DataAccessException {
		String insertSql = "SELECT * FROM USER WHERE USER_ID = ?";
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
		    statement = conn.prepareStatement(insertSql);
		    statement.setInt(1, id);
		    statement.execute();
		    
		    rs = statement.executeQuery();
		    if(rs.next()) {
		    	UserInfo user = new UserInfo();
		    	user.setUserId(rs.getInt("USER_ID"));
		    	user.setUserNum(rs.getString("USER_NUM"));
		    	user.setPassword(rs.getString("PASSWORD"));
		    	user.setName(rs.getString("NAME"));
		    	user.setDept(rs.getString("DEPT"));
		    	user.setReserveTimes(rs.getInt("RESERVE_TIMES"));
		    	user.setBreakTimes(rs.getInt("BREAK_TIMES"));
		    	return user;
		    }
		} catch (SQLException e) {
			throw new DataAccessException("error when query record", e);
		} finally {
		    if (statement != null) {
		        try {
		            statement.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
		return null;
	}
	

	

	

}
