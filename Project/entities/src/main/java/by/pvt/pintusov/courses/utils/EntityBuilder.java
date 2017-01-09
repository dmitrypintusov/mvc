package by.pvt.pintusov.courses.utils;

import by.pvt.pintusov.courses.entities.*;

import java.util.HashMap;

/**
 * Utility class for building entities
 * @author pintusov
 * @version 1.0
 */
public class EntityBuilder {
	private EntityBuilder() {}

	/**
	 * Creates user
	 * @param id - user's id
	 * @param firstName - user's first name
	 * @param lastName user's last name
	 * @param cid - course's id
	 * @param login - user's login
	 * @param password - user's password
	 * @param accessType - user's access type (0 - student, 1 - teacher)
	 * @return entity of <strong>User</strong>
	 */
	public static User buildUser (int id, String firstName, String lastName, int cid, String login, String password, int accessType) {
		User user = new User ();
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setCourseId(cid);
		user.setLogin(login);
		user.setPassword(password);
		user.setAccessType(accessType);
		return user;
	}

	/**
	 * Creates course
	 * @param id - account's id
	 * @param courseName - course's name
	 * @param hours - hours of study
	 * @param status - course's status
	 * @return entity of <strong>Course</strong>
	 */
	public static Course buildCourse (int id, String courseName, int hours, int status) {
		Course course = new Course ();
		course.setId(id);
		course.setCourseName(courseName);
		course.setHours(hours);
		course.setStatus(status);
		return course;
	}

	/**
	 * Creates operation
	 * @param id - operation's id
	 * @param userId - user's id
	 * @param courseId - course's id
	 * @param description - operation's description
	 * @param date - operation's date
	 * @return entity of <strong>Operation</strong>
	 */
	public static Operation buildOperation (int id, int userId, int courseId, String description, String date) {
		Operation operation = new Operation ();
		operation.setId(id);
		operation.setUserId(userId);
		operation.setCourseId(courseId);
		operation.setDescription(description);
		operation.setDate(date);
		return operation;
	}

	/**
	 * Creates archive
	 * @param id - archive's id
	 * @param courseId - course's id
	 * @param marks - student's marks
	 * @return entity of <strong>Archive</strong>
	 */
	public static Archive buildArchive (int id, int courseId, HashMap <User, Integer> marks) {
		Archive archive = new Archive();
		archive.setId(id);
		archive.setCourseId(courseId);
		archive.setMarks(marks);
		return archive;
	}
}
