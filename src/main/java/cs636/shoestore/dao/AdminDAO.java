package cs636.shoestore.dao;

import java.sql.Connection;
import java.sql.SQLException;

import cs636.shoestore.dao.DbDAO;

public class AdminDAO {
	private DbDAO dbDAO;
    private Connection connection;
    
	public AdminDAO(DbDAO db) throws SQLException {
		dbDAO = db;
		connection = dbDAO.getConnection();
	}

}
