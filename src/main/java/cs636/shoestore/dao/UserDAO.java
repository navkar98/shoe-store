package cs636.shoestore.dao;

import static cs636.shoestore.dao.DbConstants.CART_TABLE;
import static cs636.shoestore.dao.DbConstants.SYS_TABLE;
import static cs636.shoestore.dao.DbConstants.USER_TABLE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs636.shoestore.domain.User;

public class UserDAO {
	private DbDAO dbDAO;
    private Connection connection;
    
	public UserDAO(DbDAO db) throws SQLException {
		dbDAO = db;
		connection = dbDAO.getConnection();
	}
	
	/**
	 * 
	 * @param usr the site_user domain contains user data
	 * @throws SQLException
	 */
	public void insertUser(User usr) throws SQLException  {
		int userId = getNextUserID();
		usr.setId(userId);
		
		String simplifiedName= usr.getName().replace("'", ""); //remove 's
		
		PreparedStatement pstmt = connection.prepareStatement("insert into \"user\"(id, name, email, address, password) values(?, ?, ?, ?, ?)");
		pstmt.setLong(1, usr.getId());
		pstmt.setString(2, simplifiedName);
		pstmt.setString(3, usr.getEmail());
		pstmt.setString(4, usr.getAddress());
		pstmt.setString(5, usr.getPassword());
		try {
			
			String sqlString = "insert into \"" + USER_TABLE + 
			"\" (id, name, email, address, password) values("
			+ usr.getId() + ", '"
			+ simplifiedName + "', '" 
			+ usr.getEmail() +  "', '"
			+ usr.getAddress() + "', '"
			+ usr.getPassword() + "');";
			System.out.println(sqlString);
			pstmt.executeUpdate();
			System.out.println("Sucess");
		} finally {
			pstmt.close();
		}
	}
	/**
	 * Increase user_id by 1 in the system table
	 * @throws SQLException
	 */
	private void advanceUserID() throws SQLException
	{
		Statement stmt = connection.createStatement();
		try {
			stmt.executeUpdate(" update " + SYS_TABLE
					+ " set user_id = user_id + 1");
		} finally {
			stmt.close();
		}
	}
	
	/**
	 * Get the available user id 
	 * @return the user id available 
	 * @throws SQLException
	 */
	public int getNextUserID() throws SQLException
	{
		int nextUID;
		Statement stmt = connection.createStatement();
		try {
			ResultSet set = stmt.executeQuery(" select USER_ID from " + SYS_TABLE);
			set.next();
			nextUID = set.getInt("USER_ID");
		} finally {
			stmt.close();
		}
		advanceUserID(); // the id has been taken, so set +1 for next one
		return nextUID;
	}
	
	/**
	 * Find a user from site user table by its id
	 * (for use by DAOs to turn user_id FK into User)
	 * @param userId the user id of the user we try to find
	 * @return an User object if exist, or return null 
	 * @throws SQLException
	 */
	public User findUserByID(long userId)throws SQLException{
		User usr = null;
		Statement stmt = connection.createStatement();
		try {
			ResultSet set = stmt.executeQuery(" select * from \"" + USER_TABLE +
					"\" where user_id = " + userId);
			if (set.next()){ // if the result is not empty
				usr = new User();
				usr.setId(set.getInt("user_id"));
				usr.setName(set.getString("name"));
				usr.setPassword(set.getString("password"));
				usr.setEmail(set.getString("email"));
				usr.setAddress(set.getString("address"));
				set.close();
			}
		} finally {
			stmt.close();
		}
		return usr;
	}
	
	/**
	 * Find a user from site user table by its email
	 * @param email user's email we try to find
	 * @return an User object if exist, or return null 
	 * @throws SQLException
	 */
	public User findUserByEmail(String email) throws SQLException{
		User usr = null; 
		Statement stmt = connection.createStatement();
		try {
			ResultSet set = stmt.executeQuery(" select * from \"" + USER_TABLE +
					"\" where email = '" + email + "'");
			if (set.next()){ // if the result is not empty
				usr = new User();
				usr.setId(set.getInt("id"));
				usr.setName(set.getString("name"));
				usr.setPassword(set.getString("password"));
				usr.setEmail(set.getString("email"));
				usr.setAddress(set.getString("address"));
				set.close();
			}
		} finally {
			stmt.close();
		}
		return usr;
	}
	
	/**
	 * check login user name and password
	 * @param uid
	 * @param pwd
	 * @return
	 * @throws SQLException
	 */
	public Boolean checkUserCredentials(String email, String pwd) throws SQLException {
		Statement stmt = connection.createStatement();
		try {
			ResultSet set = stmt.executeQuery(" select * from \"" + USER_TABLE +
					"\" where email = '" + email + "'" +
					" and password = '" + pwd + "'");
			if (set.next()){ // if the result is not empty
				set.close();
				return true;
			}
		} finally {
			stmt.close();
		}
		return false;
	}
}
