package library.seat.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.mysql.jdbc.Field;
import library.seat.manage.dao.IUserDao;
import library.seat.manage.dto.PageInfo;
import library.seat.manage.dto.UserInfo;
import library.seat.manage.exception.BusinessException;
import library.seat.manage.exception.DataAccessException;
import library.seat.manage.service.IUserService;
import library.seat.manage.util.FieldValueCriteria;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
public class UserServiceImpl implements IUserService{
	
	@Inject
	private IUserDao userDao;
	
	@Override
	public int addUser(UserInfo user) throws BusinessException{
		try {
            beforeSave(user);
            int userId = userDao.add(user);
            user.setUserId(userId);
            return userId;
        } catch (DataAccessException e) {
            throw new BusinessException("error_add_rec_fail", e);
        }
	       
	}

	@Override
	public UserInfo getById(int id) throws BusinessException {
		UserInfo result = null;
		try {
			result = userDao.getById(id);
		} catch (DataAccessException e) {
			throw new BusinessException("error_query_recd_fail", e);
		}
		return result;
	}


	@Override
	public void deleteUser(int userId) throws BusinessException{
		try {
            beforeDelete(userId);
            userDao.delete(userId);
        } catch (DataAccessException e) {
            throw new BusinessException("error_delete_rec_fail", e);
        }
	}
	
	@Override
	public void updateUser(UserInfo user) throws BusinessException{
		try {
            beforeUpdate(user);
            userDao.update(user);
        } catch (DataAccessException e) {
            throw new BusinessException("error_update_rec_fail", e);
        }
	}
	
	@Override
	public PageInfo<UserInfo> queryByConditon(String userNum, String name,
			String dept, PageInfo pageInfo) throws BusinessException{
		try {
			List<FieldValueCriteria> criteria = new ArrayList<FieldValueCriteria>();
			
			if(userNum != null && !userNum.isEmpty()) {
				criteria.add(new FieldValueCriteria("USER_NUM", userNum));
			}
			if(name != null && !name.isEmpty()) {
				criteria.add(new FieldValueCriteria("NAME", name));
			}
			if(dept != null && !dept.isEmpty()) {
				criteria.add(new FieldValueCriteria("DEPT", dept));
			}
			
			pageInfo.setTotalCount(userDao.getCount(criteria));
			pageInfo = userDao.findByCriteria(criteria, pageInfo);
			return pageInfo;
		} catch (DataAccessException e) {
            throw new BusinessException("error_query_rec_fail", e);
        }
		
	}
	
	
	/**
     * 检查空Id
     *
     * @param id 数据类型记录的Id
     * @throws BusinessException 出现业务错误时将抛出异常
     */
    private void checkEmptyId(int id) throws BusinessException {
        if (id <= 0) {
            throw new BusinessException("error_empty_id");
        }
    }
	
    /**
     * 检查用户中不该为空的字段
     *
     * @param userId 用户标示Id * @throws BusinessException 出现业务错误时将抛出异常
     */
    private void checkEmptyField(UserInfo user) throws BusinessException {
        if (user.getUserNum() == null || user.getUserNum().isEmpty()) {
            throw new BusinessException("error_empty_userNum");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BusinessException("error_empty_password");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new BusinessException("error_empty_name");
        }
        if (user.getDept() == null || user.getDept().isEmpty()) {
            throw new BusinessException("error_empty_dept");
        }
    }


    /**
     * 检查重复用户标示Id    *
     * @param user 用户标示Id    * @throws BusinessException 出现业务错误时将抛出异常
     */
    private void checkDuplicatedUserId(UserInfo user) throws BusinessException {
        // 从数据库中查找同名的记录
    	List<FieldValueCriteria> criteria = new ArrayList<FieldValueCriteria>();
    	criteria.add(new FieldValueCriteria("USER_NUM", user.getUserNum()));
        List<UserInfo> userList = userDao.findByCriteria(criteria);
        if (userList != null) {
            for (UserInfo userDb : userList) {
                if (userDb.getUserNum().equals(userDb.getUserNum())) {
                    throw new BusinessException("error_duplicated_userNum");
                }
            }
        }
    }

    /**
     * 保存记录前检查  *
     * @param user 用户
     * @throws BusinessException 出现业务错误时将抛出异常
     */
    private void beforeSave(UserInfo user) throws BusinessException {
    	checkEmptyField(user);
    	checkDuplicatedUserId(user);
    }
    
    /**
     * 检查用户信息是否正确
     * 如不存在则抛出异常
     * @throws BusinessException
     */
    @Override
    public UserInfo loginCheck(String user_num, String password)
    		throws BusinessException {
    	
    	List<FieldValueCriteria> criteria = new ArrayList<FieldValueCriteria>();
    	criteria.add(new FieldValueCriteria("USER_NUM", user_num));
        List<UserInfo> userList = userDao.findByCriteria(criteria);
        if (userList.size() > 0 ) {
        	if( !userList.get(0).getPassword().equals(password)) {
        		throw new BusinessException("error_not_exsiting_user");
        	} else {
        		return userList.get(0);
        	}
        } else {
        	throw new BusinessException("error_not_exsiting_user");
        }
    	
    }
    
    /**
     * 更新记录前检查   *
     * @param user 用户     * @throws BusinessException 出现业务错误时将抛出异常
     */
    private void beforeUpdate(UserInfo user) throws BusinessException {
    	checkEmptyField(user);
    }
    /**
     * 删除记录前处理   *
     * @param userId 用户标识Id
     * @throws BusinessException 出现业务错误时将抛出异常
     */
    private void beforeDelete(int userId) throws BusinessException {
        checkEmptyId(userId);
    }

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}


}
