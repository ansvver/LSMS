package library.seat.manage.service;

import library.seat.manage.dto.PageInfo;
import library.seat.manage.dto.UserInfo;
import library.seat.manage.exception.BusinessException;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 2-1-2013 13:20:00
 */
public interface IUserService {
	
	int addUser(UserInfo user) throws BusinessException;
	
	void updateUser(UserInfo user) throws BusinessException;
	
	void deleteUser(int userId) throws BusinessException;
	
	UserInfo loginCheck(String user_num, String password) throws BusinessException;
	
	PageInfo<UserInfo> queryByConditon(String userNum, String name, 
			String dept, PageInfo pageInfo);
	
	UserInfo getById(int id) throws BusinessException;
	
}
