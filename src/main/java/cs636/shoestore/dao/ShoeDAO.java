package cs636.shoestore.dao;

import static cs636.shoestore.dao.DbConstants.SHOE_TABLE;
import static cs636.shoestore.dao.DbConstants.USER_TABLE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cs636.shoestore.domain.Filter;
import cs636.shoestore.domain.Shoe;
import cs636.shoestore.domain.User;

public class ShoeDAO {
	private DbDAO dbDAO;
    private Connection connection;
    
	public ShoeDAO(DbDAO db) throws SQLException {
		dbDAO = db;
		connection = dbDAO.getConnection();
	}
	
	public List<Shoe> findAllShoes() throws SQLException{
		List<Shoe> shoes = new ArrayList<Shoe>();
		Statement stmt = connection.createStatement();
		try {
			String query = " select * from " + SHOE_TABLE;
			ResultSet rs = stmt.executeQuery(query );
			System.out.println(query);
			while (rs.next()){ // if the result is not empty
				Shoe shoe = new Shoe(rs.getInt("id"), 
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getInt("brand_id"), 
						rs.getInt("tag_id"), 
						rs.getInt("material_id"), 
						rs.getInt("feature_id"), 
						rs.getInt("shoe_type_id"), 
						rs.getInt("size_id"), 
						rs.getString("color_code"),
						rs.getDouble("height"),
						rs.getDouble("width"),
						rs.getDouble("len")); 
				shoes.add(shoe);
			}
			rs.close();
		} finally {
			stmt.close();
		}
		
		return shoes;
	}
	
	public List<Shoe> findShoesByFilter(Filter filter) throws SQLException{
		List<Shoe> shoes = new ArrayList<Shoe>();
		Statement stmt = connection.createStatement();
		try {
			
			String query = createFilterQuery(filter); 
			System.out.println(query);
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()){ // if the result is not empty
				Shoe shoe = new Shoe(rs.getInt("id"), 
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getInt("brand_id"), 
						rs.getInt("tag_id"), 
						rs.getInt("material_id"), 
						rs.getInt("feature_id"), 
						rs.getInt("shoe_type_id"), 
						rs.getInt("size_id"), 
						rs.getString("color_code"),
						rs.getDouble("height"),
						rs.getDouble("width"),
						rs.getDouble("len")); 
				shoes.add(shoe);
			}
			rs.close();
		} finally {
			stmt.close();
		}
		
		return shoes;
	}
	
	public String findBrandByName(String brand)throws SQLException{
		int brandId = -1;
		
		PreparedStatement pstmt = connection.prepareStatement("SELECT id FROM brand WHERE lower(NAME) = ?");
		pstmt.setString(1, brand.toLowerCase());
		
		try {
			ResultSet set = pstmt.executeQuery();
			if (set.next()){ // if the result is not empty
				brandId = set.getInt("id");
				set.close();
			}
		} finally {
			pstmt.close();
		}
		return Integer.toString(brandId);
	}
	
	public String findTagByName(String tag)throws SQLException{
		int brandId = -1;
		
		PreparedStatement pstmt = connection.prepareStatement("SELECT id FROM tags WHERE lower(NAME) = ?");
		pstmt.setString(1, tag.toLowerCase());
		
		try {
			ResultSet set = pstmt.executeQuery();
			if (set.next()){ // if the result is not empty
				brandId = set.getInt("id");
				set.close();
			}
		} finally {
			pstmt.close();
		}
		return Integer.toString(brandId);
	}

	private String createFilterQuery(Filter filter) {
		String query = "select * from " + SHOE_TABLE + " where ";
		try {			
			if(filter.getBrand() != null && filter.getBrand() != "") {
				String id = findBrandByName(filter.getBrand());
				query = query + " brand_id = " + id + " and ";
			}
			
			if(filter.getTag() != null && filter.getTag() != "") {
				query = query + " tag_id = " + findTagByName(filter.getTag()) + " and ";
			}
			
			if(filter.getMinPrice() != null && filter.getMinPrice() != "" && filter.getMaxPrice() != null && filter.getMaxPrice() != "") {
				query = query + " price between " + filter.getMinPrice() + " and " + filter.getMaxPrice() + " and " ;
			}else if(filter.getMinPrice() != null  && filter.getMinPrice() != "") {
				query = query + " price > " + filter.getMinPrice() + " and ";
			}else if(filter.getMaxPrice() != null && filter.getMaxPrice() != "" ) {
				query = query + " price < " + filter.getMaxPrice() + " and ";
			} 
			
			if(filter.getColorCode() != null && filter.getColorCode() != "")  {
				query = query + " color_code = \'" + filter.getColorCode() + "\' and ";
			}
			
			query = query.substring(0, query.length() - 4) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return query;
	}
}
