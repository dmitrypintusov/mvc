package by.pvt.pintusov.courses.constants;

/**
 * SqlRequest statements
 * @author pintusov
 * @version 1.0
 */
public class SqlRequest {

	public static final String ADD_USER = "INSERT INTO users (first_name, last_name, login, password) VALUES (?, ?, ?, ?)";
	public static final String GET_ALL_STUDENTS = "SELECT first_name, last_name FROM users WHERE access_type = 1 ORDER BY last_name";
	public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE uid = ?";
	public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
	public static final String GET_LAST_USER_ID = "SELECT MAX(uid) FROM users";
	public static final String DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE login = ?";

	public static final String CHECK_LOGIN = "SELECT login FROM users WHERE login = ?";
	public static final String CHECK_AUTHORIZATION = "SELECT login, password FROM users WHERE login = ? AND password = ?";

	public static final String ADD_COURSE = "INSERT INTO courses (name, hours, status) VALUES (?, ?, ?)";
	public static final String GET_ALL_COURSES = "SELECT * FROM courses";
	public static final String GET_COURSE_BY_ID = "SELECT * FROM courses WHERE cid = ?";
	public static final String GET_LAST_COURSE_ID = "SELECT MAX(cid) FROM courses";
	public static final String GET_ACTIVE_COURSES = "SELECT * FROM courses WHERE status = 0";
	public static final String CHECK_COURSE_STATUS = "SELECT status FROM courses WHERE status = ?";
	public static final String UPDATE_COURSE_STATUS = "UPDATE status FROM courses SET status = ? WHERE cid = ?";
	public static final String DELETE_COURSE_BY_COURSENAME = "DELETE FROM courses WHERE name = ?";

	public static final String ADD_OPERATION = "INSERT INTO operations (uid, cid, description, date) VALUES (?, ?, ?, ?)";
	public static final String GET_ALL_OPERATIONS = "SELECT * FROM operations, users WHERE user.uid = operation.uid ORDER BY date";
	public static final String GET_OPERATION_BY_ID = "SELECT * FROM operations WHERE oid = ?";
	public static final String DELETE_OPERATION_BY_ID = "DELETE FROM operations WHERE oid = ?";

	private SqlRequest () {}
}
