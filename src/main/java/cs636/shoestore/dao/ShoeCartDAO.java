package cs636.shoestore.dao;

import static cs636.shoestore.dao.DbConstants.CART_TABLE;
import static cs636.shoestore.dao.DbConstants.INVENTORY_TABLE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs636.shoestore.domain.Cart;
import cs636.shoestore.domain.Inventory;

public class ShoeCartDAO {
	private DbDAO dbDAO;
    private Connection connection;
    
	public ShoeCartDAO(DbDAO db) throws SQLException {
		dbDAO = db;
		connection = dbDAO.getConnection();
	}
	
	public void addShoeInCart(Cart shoe) throws SQLException{
		PreparedStatement pstmt = connection.prepareStatement("insert into cart values(?, ?, ?, ?, ?)");
		pstmt.setInt(1, dbDAO.findNextId(CART_TABLE));
		pstmt.setString(2, shoe.getShoeId());
		pstmt.setString(3, shoe.getDiscountId());
		pstmt.setString(4, shoe.getFinalPrice());
		pstmt.setString(5, shoe.getUserId());
		
		try {
			updateShoeInventory(shoe.getShoeId());
			
			String query = "insert into " + CART_TABLE + " values( " + 
					dbDAO.findNextId(CART_TABLE) + ", " + 
					shoe.getShoeId()+ ", " + 
					shoe.getDiscountId() + ", " +
					shoe.getFinalPrice()+ ", " +
					shoe.getUserId() + " );";
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
		
		return;
	}
	
	public int getShoeQuantity(String shoeId) throws SQLException{
		PreparedStatement pstmt = connection.prepareStatement("SELECT QUANTITY FROM INVENTORY where SHOE_ID = ?");
		pstmt.setString(1, shoeId);
		
		int quantity = 0;
		try {
			String query = "select QUANTITY from " + INVENTORY_TABLE + " where SHOE_ID = " + shoeId + " ; ";
			ResultSet set = pstmt.executeQuery();
			if(set.next()) {
				quantity = set.getInt("QUANTITY");
			}
		} finally {
			pstmt.close();
		}
		
		return quantity;
	}

	private void updateShoeInventory(String shoeId) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement("update INVENTORY set quantity = ? where SHOE_ID = ?");
		pstmt.setString(1, Integer.toString(getShoeQuantity(shoeId) - 1));
		pstmt.setString(2, shoeId);
		try {
			String query = "update " + INVENTORY_TABLE + " set quantity = " + 
					 " where shoe_id = " + 
					shoeId + " ; ";
			pstmt.executeUpdate();
		} finally {
			pstmt.close();
		}
		
		return;
		
	}
}
