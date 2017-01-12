package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.constants.UserType;
import by.pvt.pintusov.courses.dao.Impl.CourseDaoImpl;
import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.entities.Course;
import by.pvt.pintusov.courses.entities.Operation;
import by.pvt.pintusov.courses.entities.User;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import org.junit.*;

/**
 * Created by USER on 09.01.17.
 */
public class UserServiceImplTest {
	private User user;

	@Before
	public void setUp(){
		user = EntityBuilder.buildUser("TEST", "TEST", "TEST", "TEST", 0);
	}

	@After
	public void tearDown() throws Exception{
		user = null;
	}

	@Test
	public void testGetInstance() throws Exception {
		UserServiceImpl instance1 = UserServiceImpl.getInstance();
		UserServiceImpl instance2 = UserServiceImpl.getInstance();
		Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
	}

	@Ignore
	@Test
	public void testCheckUserAuthorization() throws Exception {
		UserDaoImpl.getInstance().add(user);
		boolean expected = true;
		boolean actual = UserServiceImpl.getInstance().checkUserAuthorization(user.getLogin(), user.getPassword());
		Assert.assertEquals(new Boolean(expected), new Boolean(actual));
	}

	@Ignore
	@Test
	public void testGetUserByLogin() throws Exception {
		UserDaoImpl.getInstance().add(user);
		int userActualId = UserDaoImpl.getInstance().getMaxId();
		user.setId(userActualId);
		User actual = UserServiceImpl.getInstance().getUserByLogin(user.getLogin());
		Assert.assertEquals(user, actual);
	}

	@Ignore
	@Test
	public void testCheckAccessType() throws Exception {
		UserDaoImpl.getInstance().add(user);
		UserType actual = UserServiceImpl.getInstance().checkAccessType(user);
		Assert.assertEquals(user.getAccessType(), actual.ordinal());
	}

	@Ignore
	@Test
	public void testCheckIsNewUser() throws Exception {
		boolean expected = true;
		boolean actual = UserServiceImpl.getInstance().checkIsNewUser(user);
		Assert.assertEquals(new Boolean(expected), new Boolean(actual));
	}
}
