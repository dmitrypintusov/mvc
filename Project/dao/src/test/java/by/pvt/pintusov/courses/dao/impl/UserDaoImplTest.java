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
	private User user;

	@Before
	public void setUp() throws Exception {
		user = EntityBuilder.buildUser("TEST", "TEST","TEST", "TEST", 0);
	}

	@After
	public void tearDown () throws Exception {
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
		UserDaoImpl.getInstance().add(user);
		User actual = UserDaoImpl.getInstance().getByLogin(user.getLogin());
		Assert.assertEquals(user, actual);
		UserDaoImpl.getInstance().deleteByLogin(user.getLogin());
	}

	@Ignore
	@Test
	public void testDelete() throws Exception {
		UserDaoImpl.getInstance().add(user);
		UserDaoImpl.getInstance().deleteByLogin(user.getLogin());
		User actual = UserDaoImpl.getInstance().getByLogin(user.getLogin());
		Assert.assertNull(actual);
	}

	@Ignore
	@Test
	public void testIsAuthorized () throws Exception {
		UserDaoImpl.getInstance().add(user);
		boolean isAuthorised = UserDaoImpl.getInstance().isAuthorized(user.getLogin(), user.getPassword());
		Assert.assertTrue(isAuthorised);
		UserDaoImpl.getInstance().deleteByLogin(user.getLogin());
	}

	@Ignore
	@Test
	public void testGetById () throws Exception {
		UserDaoImpl.getInstance().add(user);
		User actual = UserDaoImpl.getInstance().getById(UserDaoImpl.getInstance().getMaxId());
		Assert.assertEquals(user, actual);
		UserDaoImpl.getInstance().deleteByLogin(user.getLogin());
	}

	@Ignore
	@Test
	public void testGetByLogin () throws Exception {
		UserDaoImpl.getInstance().add(user);
		User actual = UserDaoImpl.getInstance().getByLogin(user.getLogin());
		Assert.assertEquals(user, actual);
		UserDaoImpl.getInstance().deleteByLogin(user.getLogin());
	}

	@Ignore
	@Test
	public void testIsNewUser () throws Exception {
		UserDaoImpl.getInstance().add(user);
		boolean isNew = UserDaoImpl.getInstance().isNewUser(user.getLogin());
		Assert.assertFalse(isNew);
		UserDaoImpl.getInstance().deleteByLogin(user.getLogin());
	}

	@Ignore
	@Test
	public void testGetMaxId () throws Exception {
		UserDaoImpl.getInstance().add(user);
		int expected = user.getId();
		int actual = UserDaoImpl.getInstance().getMaxId();
		Assert.assertEquals(expected, actual);
	}
}
