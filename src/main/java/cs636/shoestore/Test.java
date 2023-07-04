package cs636.shoestore;

import java.sql.Connection;

import cs636.shoestore.config.ShoeStoreConfig;
import cs636.shoestore.dao.DbDAO;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ShoeStoreConfig.configureServices();
			System.out.print("Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
