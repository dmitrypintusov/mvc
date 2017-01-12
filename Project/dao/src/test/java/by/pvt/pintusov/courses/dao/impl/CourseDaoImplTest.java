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
	private Course course;

	@Before
	public void setUp(){
		course = EntityBuilder.buildCourse("TEST", 100, 0);
	}

	@After
	public void tearDown(){
		course = null;
	}

	@Test
	public void testGetInstance() throws Exception {
		CourseDaoImpl instance1 = CourseDaoImpl.getInstance();
		CourseDaoImpl instance2 = CourseDaoImpl.getInstance();
		Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
	}

	@Ignore
	@Test
	public void testAdd() throws Exception{
		CourseDaoImpl.getInstance().add(course);
		Course actual = CourseDaoImpl.getInstance().getById(course.getId());
		Assert.assertEquals(course, actual);
		CourseDaoImpl.getInstance().deleteByCourseName(course.getCourseName());
	}

	@Ignore
	@Test
	public void testGetById() throws Exception {
		Course actual = CourseDaoImpl.getInstance().getById(course.getId());
		Assert.assertEquals(course, actual);
	}

	@Ignore
	@Test
	public void testUpdateAmount() throws Exception {
		CourseDaoImpl.getInstance().add(course);
		int adding = 100;
		course.setHours(course.getHours() + adding);
		CourseDaoImpl.getInstance().updateCourseStatus(adding, course.getId());
		Course actual = CourseDaoImpl.getInstance().getById(course.getId());
		CourseDaoImpl.getInstance().deleteByCourseName(course.getCourseName());
		Assert.assertEquals(course, actual);
	}

	@Ignore
	@Test
	public void testDelete() throws Exception{
		CourseDaoImpl.getInstance().add(course);
		CourseDaoImpl.getInstance().deleteByCourseName(course.getCourseName());
		Course actual = CourseDaoImpl.getInstance().getById(course.getId());
		Assert.assertNull(actual);
	}
}
