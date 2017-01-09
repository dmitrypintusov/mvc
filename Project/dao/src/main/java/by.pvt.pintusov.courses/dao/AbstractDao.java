package by.pvt.pintusov.courses.dao;

import by.pvt.pintusov.courses.entities.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Abstract Dao class implements Dao interface
 * @author pintusov
 * @version 1.0
 */

public abstract class AbstractDao <T extends Entity> implements IDao <T> {
	protected Connection connection;
	protected PreparedStatement statement;
	protected ResultSet result;
}
