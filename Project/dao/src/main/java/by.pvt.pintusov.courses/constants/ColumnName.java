package by.pvt.pintusov.courses.constants;

/**
 * Database column name
 * @author pintusov
 * @version 1.0
 */
public class ColumnName {

	/**
	 * User table
	 */
	public static final String USER_ID = "uid";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_LAST_NAME = "last_name";
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_ACCESS_TYPE = "access_type";

	/**
	 * Course table
	 */
	public static final String COURSES_ID = "cid";
	public static final String COURSES_NAME = "name";
	public static final String COURSES_HOURS = "hours";
	public static final String COURSES_STATUS = "status";

	/**
	 * Operation table
	 */
	public static final String OPERATION_ID = "oid";
	public static final String OPERATION_DESCRIPTION = "description";
	public static final String OPERATION_DATE = "date";

	/**
	 * Archive table
	 */
	public static final String ARCHIVE_ID = "aid";
	public static final String STUDENTS_MARKS = "marks";

	private ColumnName () {}
}
