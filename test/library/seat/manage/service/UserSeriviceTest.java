package library.seat.manage.service;

import play.modules.guice.InjectSupport;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import library.seat.manage.dao.IUserDao;
import library.seat.manage.dto.PageInfo;
import library.seat.manage.dto.UserInfo;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 1-1-2013 22:50:00
 */
@InjectSupport
public class UserSeriviceTest extends BaseUnitTest{
	
	@Inject
	private static IUserService userService;
	
	private UserInfo user = null;
	@Before
	public void before() {
		Assert.assertNotNull(userService);
		user = new UserInfo();
		user.setUserNum("3109006385");
		user.setPassword("3333");
		user.setName("mahs");
		user.setDept("computer");
		user.setReserveTimes(0);
		user.setBreakTimes(0);
	}
	
	@Test
	public void testAddUser() {
		prepareDatabase("test/scripts/UserBaseTest_testBaseCRUD.sql");
		userService.addUser(user);
	}
	
	@Test
	public void testUpdateUser() {
		prepareDatabase("test/scripts/UserBaseTest_testBaseCRUD.sql");
		userService.addUser(user);
		PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>();
		pageInfo = userService.queryByConditon(null, "mahs", null, pageInfo);
		UserInfo user = pageInfo.getResult().get(0);
		user.setName("tanwl");
		userService.updateUser(user);
	}

	@Test
	public void testDeleteUser() {
		prepareDatabase("test/scripts/UserBaseTest_testBaseCRUD.sql");
		userService.addUser(user);
		PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>();
		pageInfo = userService.queryByConditon(null, "mahs", null, pageInfo);
		UserInfo user = pageInfo.getResult().get(0);
		userService.deleteUser(user.getUserId());
	}

}
