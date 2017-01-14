package by.pvt.pintusov.courses.dao.impl;

import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.entities.User;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import org.junit.*;

/**
 * Test User Dao
 * @author pintusov
 * @version 1.0
 */

public class UserDaoImplTest {
	private static User user;

	@BeforeClass
	public static void setUp() throws Exception {
		user = EntityBuilder.buildUser("TEST", "TEST","TEST", "TEST", 0);
		UserDaoImpl.getInstance().add(user);
	}

	@AfterClass
	public static void tearDown () throws Exception {
		UserDaoImpl.getInstance().deleteByLogin(user.getLogin());
		user = null;
	}

	@Test
	public void testGetInstance() throws Exception {
		UserDaoImpl instance1 = UserDaoImpl.getInstance();
		UserDaoImpl instance2 = UserDaoImpl.getInstance();
		Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
	}

	@Ignore
	@Test
	public void testAdd() throws Exception {
		User actual = UserDaoImpl.getInstance().getByLogin(user.getLogin());
		Assert.assertEquals(user, actual);
	}

	@Ignore
	@Test
	public void testDeleteByLogin() throws Exception {
		User actual = UserDaoImpl.getInstance().getByLogin(user.getLogin());
		Assert.assertNull(actual);
	}

	@Test
	public void testIsAuthorized () throws Exception {
		boolean isAuthorised = UserDaoImpl.getInstance().isAuthorized(user.getLogin(), user.getPassword());
		Assert.assertTrue(isAuthorised);
	}

	@Test
	public void testGetById () throws Exception {
		User actual = UserDaoImpl.getInstance().getById(UserDaoImpl.getInstance().getMaxId());
		Assert.assertEquals(user, actual);
	}

	@Test
	public void testGetByLogin () throws Exception {
		User actual = UserDaoImpl.getInstance().getByLogin(user.getLogin());
		Assert.assertEquals(user, actual);
	}

	@Test
	public void testIsNewUser () throws Exception {
		boolean isNew = UserDaoImpl.getInstance().isNewUser(user.getLogin());
		Assert.assertFalse(isNew);
	}

	@Ignore
	@Test
	public void testGetMaxId () throws Exception {
		int expected = user.getId();
		int actual = UserDaoImpl.getInstance().getMaxId();
		Assert.assertEquals(expected, actual);
	}
}
