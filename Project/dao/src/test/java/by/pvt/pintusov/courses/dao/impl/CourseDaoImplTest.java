package by.pvt.pintusov.courses.dao.impl;

import by.pvt.pintusov.courses.dao.Impl.CourseDaoImpl;
import by.pvt.pintusov.courses.entities.Course;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import org.junit.*;

/**
 * Test Course Dao
 * @author pintusov
 * @version 1.0
 */

public class CourseDaoImplTest {
	private static Course course;

	@BeforeClass
	public static void setUp() throws Exception {
		course = EntityBuilder.buildCourse("TEST", 100, 0);
		CourseDaoImpl.getInstance().add(course);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		CourseDaoImpl.getInstance().deleteByCourseName(course.getCourseName());
		course = null;
	}

	@Test
	public void testGetInstance() throws Exception {
		CourseDaoImpl instance1 = CourseDaoImpl.getInstance();
		CourseDaoImpl instance2 = CourseDaoImpl.getInstance();
		Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
	}

	@Test
	public void testAdd() throws Exception{
		Course actual = CourseDaoImpl.getInstance().getByCourseName(course.getCourseName());
		Assert.assertEquals(course, actual);

	}

	@Ignore
	@Test
	public void testGetById() throws Exception {
		Course actual = CourseDaoImpl.getInstance().getById(course.getId());
		Assert.assertEquals(course, actual);
	}

	@Ignore
	@Test
	public void testUpdateCourseStatus() throws Exception {
		int newStatus = 1;
		course.setStatus(newStatus);
		CourseDaoImpl.getInstance().updateCourseStatus(course.getCourseName(), newStatus);
		Course actual = CourseDaoImpl.getInstance().getByCourseName(course.getCourseName());
		Assert.assertEquals(course, actual);
	}

	@Test
	public void testDelete() throws Exception{
		CourseDaoImpl.getInstance().deleteByCourseName(course.getCourseName());
		Course actual = CourseDaoImpl.getInstance().getByCourseName(course.getCourseName());
		Assert.assertNull(actual);
	}
}
