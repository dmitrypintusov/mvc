package by.pvt.pintusov.courses.dao.impl;

import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.entities.User;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by USER on 26.12.16.
 */
public class UserDaoImplTest {
	//@Ignore
	@Test
	public void testGetInstance() throws Exception {
		UserDaoImpl instance1 = UserDaoImpl.getInstance();
		UserDaoImpl instance2 = UserDaoImpl.getInstance();
		Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
	}

	@Ignore
	@Test
	public void testAdd() throws Exception {
		User expected = EntityBuilder.buildUser(100, "first", "last", 100, "login", "password", 1);
		UserDaoImpl.getInstance().add(expected);
		User actual = UserDaoImpl.getInstance().getByLogin(expected.getLogin());
		Assert.assertEquals("Test done", expected, actual);
		UserDaoImpl.getInstance().delete(expected.getId());
	}

	@Ignore
	@Test
	public void testDelete() throws Exception {
		User user = EntityBuilder.buildUser(111, "first", "last", 111, "login", "password", 1);
		UserDaoImpl.getInstance().add(user);
		UserDaoImpl.getInstance().delete(user.getId());
		User actual = UserDaoImpl.getInstance().getById(user.getId());
		Assert.assertNull(actual);
	}
}
