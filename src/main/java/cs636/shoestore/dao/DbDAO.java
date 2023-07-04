package cs636.shoestore.dao;

import static cs636.shoestore.dao.DbConstants.DB_URL;
import static cs636.shoestore.dao.DbConstants.DB_USER;
import static cs636.shoestore.dao.DbConstants.DB_PASSWORD;
import static cs636.shoestore.dao.DbConstants.CART_TABLE;
import static cs636.shoestore.dao.DbConstants.SYS_TABLE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbDAO {
	   
	private Connection connection;
	
	public DbDAO() throws SQLException {
		System.out.println("Connecting to database....");
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		System.out.println("Connected to database....");
	}

	// package protection: no need to call this from service layer
	Connection getConnection() {
		return connection;
	}
	public void close() throws SQLException {
		connection.close();  // this object opened it, so it gets to close it
	}

	// bring DB back to original state
	public void initializeDb() throws SQLException {
		// drop tables with FK cols before the tables they refer to
		clearTable(CART_TABLE);
		clearTable(SYS_TABLE);
		initSysTable();
	}

	private void clearTable(String tableName) throws SQLException {
		Statement stmt = connection.createStatement();
		try {
			stmt.execute("delete from " + tableName);
		} finally {
			stmt.close();
		}
	}
	
	private void initSysTable() throws SQLException {
		Statement stmt = connection.createStatement();
		try {
			stmt.execute("update " + SYS_TABLE + " set cart = 1");
		} finally {
			stmt.close();
		}
	}
	
	// Note package scope: no need to call this from service layer
	void advanceId(String columnName) throws SQLException
	{
		Statement stmt = connection.createStatement();
		try {
			stmt.executeUpdate(" update " + SYS_TABLE
					+ " set " + columnName + " = " + columnName + " + 1");
		} finally {
			stmt.close();
		}
	}

	// This shows one good way to produce new primary key value--use another
	// table's data to specify the next value
	// Here we use SYS_TABLE columns next_order_id, etc.
	int findNextId(String columnName) throws SQLException
	{
		int nextId;
		Statement stmt = connection.createStatement();
		try {
			ResultSet set = stmt.executeQuery(" select " + columnName + " from " + SYS_TABLE);
			set.next();
			nextId = set.getInt(columnName);
		} finally {
			stmt.close();
		}
		advanceId(columnName);
		return nextId;
	}
	


}
