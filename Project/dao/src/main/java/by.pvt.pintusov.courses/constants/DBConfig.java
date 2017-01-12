package by.pvt.pintusov.courses.constants;
/**
 * Database configuration properties
 * @author pintusov
 * @version 1.0
 */
public class DBConfig {
	public static final String DB = "database";
	public static final String DRIVER = "database.driver";
	public static final String URL = "database.url";
	public static final String USER = "database.user";
	public static final String PASSWORD = "database.password";

	//properties for HikariCP
	public static final String HIKARI = "hikari";
	public static final String HIKARI_DRIVER = "dataSourceClassName";
	public static final String HIKARI_USER = "dataSource.user";
	public static final String HIKARI_PASSWORD = "dataSource.password";
	public static final String HIKARI_URL = "dataSource.jdbcUrl";
}
