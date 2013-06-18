package library.seat.manage.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import play.db.DB;

import library.seat.manage.dao.BaseDao;
import library.seat.manage.dao.IDeskDao;
import library.seat.manage.dto.DeskInfo;
import library.seat.manage.dto.PageInfo;
import library.seat.manage.exception.DataAccessException;
import library.seat.manage.util.FieldValueCriteria;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
public class DeskDaoImpl extends BaseDao implements IDeskDao {

	private static final String TABLE = "DESK"; 
	
	@Override
	public int add(DeskInfo desk) throws DataAccessException {
		return 0;
	}

	@Override
	public void update(DeskInfo desk) throws DataAccessException {
		
	}

	@Override
	public void delete(int deskId) throws DataAccessException {
		
	}

	@Override
	public int getDeskId(int floor, String block, int desk_num)
			throws DataAccessException {
		String queryRecs = "select * from " + TABLE + " where FLOOR = ? and BLOCK = ? and DESK_NUM = ? ";
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<DeskInfo> deskList = new ArrayList<DeskInfo>();
		try {
			statement = conn.prepareStatement(queryRecs);
			statement.setInt(1, floor);
			statement.setString(2, block);
			statement.setInt(3, desk_num);
		    rs = statement.executeQuery();
		    while(rs.next()) {
		    	DeskInfo desk = new DeskInfo();
		    	desk.setDeskId(rs.getInt("DESK_ID"));
		    	desk.setFloor(rs.getInt("FLOOR"));
		    	desk.setBlock(rs.getString("BLOCK"));
		    	desk.setDeskNum(rs.getInt("DESK_NUM"));
		    	desk.setIsAble(rs.getBoolean("IS_ABLE"));
		    	deskList.add(desk);
		    }
		    if(deskList.size() != 1) {
		    	throw new DataAccessException("ERROR : more than one desk found have the same FLOOR & BLOCK & DESK_NUM");
		    }
		    return deskList.get(0).getDeskId();
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
	public int getCount(List<FieldValueCriteria> criteria)
			throws DataAccessException {
		return 0;
	}

	@Override
	public List<DeskInfo> findByCriteria(List<FieldValueCriteria> criteria)
			throws DataAccessException {
		StringBuilder queryRecs = new StringBuilder("select * from " + TABLE + " where 1 = 1");
		appendCriterias(criteria, queryRecs);
		
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<DeskInfo> userList = new ArrayList<DeskInfo>();
		try {
			statement = conn.prepareStatement(queryRecs.toString());
		    for(int i=0; i<criteria.size(); i++) {
		    	statement.setObject(i+1, criteria.get(i).getFieldValue());
		    }
		    rs = statement.executeQuery();
		    while(rs.next()) {
		    	DeskInfo desk = new DeskInfo();
		    	desk.setDeskId(rs.getInt("DESK_ID"));
		    	desk.setFloor(rs.getInt("FLOOR"));
		    	desk.setBlock(rs.getString("BLOCK"));
		    	desk.setDeskNum(rs.getInt("DESK_NUM"));
		    	desk.setIsAble(rs.getBoolean("IS_ABLE"));
		    	userList.add(desk);
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
	public PageInfo<DeskInfo> findByCriteria(List<FieldValueCriteria> criteria,
			PageInfo pageInfo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}


}
