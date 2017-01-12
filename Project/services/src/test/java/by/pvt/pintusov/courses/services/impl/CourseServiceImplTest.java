package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.constants.CourseStatus;
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
public class CourseServiceImplTest {
	private Course course;
	private User user;
	private Operation operation;

	@Before
	public void setUp(){
		course = EntityBuilder.buildCourse("TEST", 100, 0);
		user = EntityBuilder.buildUser("TEST", "TEST", "TEST", "TEST", 0);
		operation = EntityBuilder.buildOperation(100, user.getId(), user.getCourseId(), "TEST", "01.01.01");
	}

	@After
	public void tearDown() throws Exception{
		course = null;
		user = null;
		operation = null;
	}

	@Ignore
	@Test
	public void testGetInstance() throws Exception {
		CourseServiceImpl instance1 = CourseServiceImpl.getInstance();
		CourseServiceImpl instance2 = CourseServiceImpl.getInstance();
		Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
	}

	@Ignore
	@Test
	public void testAdd() throws Exception {
		CourseServiceImpl.getInstance().add(course);
		Course actual = CourseServiceImpl.getInstance().getById(course.getId());
		CourseDaoImpl.getInstance().deleteByCourseName(course.getCourseName());
		Assert.assertEquals(course, actual);
	}

	@Ignore
	@Test
	public void testGetById() throws Exception {
		course = EntityBuilder.buildCourse("TEST", 0, 0);
		Course actual = CourseServiceImpl.getInstance().getById(course.getId());
		Assert.assertEquals(course, actual);
	}

	@Ignore
	@Test
	public void testEndCourse() throws Exception {
		CourseDaoImpl.getInstance().add(course);
		UserDaoImpl.getInstance().add(user);
		user.setId(UserDaoImpl.getInstance().getMaxId());
		CourseServiceImpl.getInstance().endCourse(user, operation.getDescription(), "DATE");
		long expected = CourseStatus.CLOSED;
		long actual = CourseDaoImpl.getInstance().getById(course.getId()).getStatus();
		CourseDaoImpl.getInstance().deleteByCourseName(course.getCourseName());
		Assert.assertEquals(expected, actual);
	}
}
