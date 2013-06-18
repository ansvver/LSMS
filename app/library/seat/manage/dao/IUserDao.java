package library.seat.manage.dao;

import java.util.List;

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
public interface IUserDao {
	
	int add(UserInfo user) throws DataAccessException;
	
	void update(UserInfo user) throws DataAccessException;
	
	void delete(int id) throws DataAccessException;
	
	int getCount(List<FieldValueCriteria> criteria) throws DataAccessException;
	
	List<UserInfo> findByCriteria(List<FieldValueCriteria> criteria) throws DataAccessException;
	
	PageInfo<UserInfo> findByCriteria(
			List<FieldValueCriteria> criteria, PageInfo pageInfo) throws DataAccessException;

	UserInfo getById(int id) throws DataAccessException;
}
