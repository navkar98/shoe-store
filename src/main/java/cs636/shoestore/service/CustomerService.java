package cs636.shoestore.service;

import cs636.shoestore.dao.UserDAO;

import cs636.shoestore.domain.User;

import cs636.shoestore.domain.Cart;
import cs636.shoestore.domain.Shoe;
import cs636.shoestore.domain.Filter;

import java.util.List;

import cs636.shoestore.dao.DbDAO;
import cs636.shoestore.dao.ShoeCartDAO;
import cs636.shoestore.dao.ShoeDAO;

public class CustomerService {
	private DbDAO dbDAO;
	private UserDAO userDAO;
	private ShoeCartDAO shoeCartDAO;
	private ShoeDAO shoeDAO;
	private String userId;
	
	public CustomerService(DbDAO dbDAO, UserDAO userDAO, ShoeCartDAO shoeCartDAO, ShoeDAO shoeDAO) {
		this.dbDAO = dbDAO;
		this.userDAO = userDAO;
		this.shoeCartDAO = shoeCartDAO;
		this.shoeDAO = shoeDAO;
	}
	
	public void initializeDB()throws ServiceException {
		try {
			this.dbDAO.initializeDb();
		} catch (Exception e) { // any exception
			throw new ServiceException(
					"Can't initialize DB: (probably need to load DB)", e);
		}
	}
	
	public boolean registerUser(String name, String pwd, String email, String addr) throws ServiceException {
		User user = null;
		try {
			if (user == null) { // this user has not registered yet
				user = new User();
				user.setName(name);
				user.setPassword(pwd);
				user.setEmail(email);
				user.setAddress(addr);
				this.userDAO.insertUser(user);
				return true;
			}
		} catch (Exception e) {
			throw new ServiceException("Error while registering user: ", e);
		}
		return false;
	}
	
	public Boolean checkAdminLogin(String email, String password) throws ServiceException {
		try {
			Boolean b = this.userDAO.checkUserCredentials(email, password);
			return b;
		} catch (Exception e)
		{
			throw new ServiceException("Check login error: ", e);
		}
	}
	
	public void processCart(String shoeId, String discountId, String finalPrice, String userId) throws ServiceException {
		try {
			Cart cart = new Cart(-1, discountId, finalPrice, userId, shoeId);
			
			this.shoeCartDAO.addShoeInCart(cart);
		} catch (Exception e) {
			throw new ServiceException("Cart was not processed successfully: ", e);
		}
	}
	
	public List<Shoe> getShoeList(Filter filter) throws ServiceException {
		List<Shoe> shoeList;
		try {
			if(filter != null) {
				shoeList = this.shoeDAO.findShoesByFilter(filter);
			}else {
				shoeList = this.shoeDAO.findAllShoes();
			}
			
		} catch (Exception e) {
			throw new ServiceException("Issue in getting shoe list: ", e);
		}
		return shoeList;
	}

	public User checkEmailExists(String email) throws ServiceException {
		try {
			User usr = this.userDAO.findUserByEmail(email);
			return usr;
		} catch (Exception e)
		{
			throw new ServiceException("Check login error: ", e);
		}
	}
	
	public String getUserIdByEmail(String email) throws ServiceException {
		try {
			User usr = this.userDAO.findUserByEmail(email);
			return Long.toString(usr.getId());
		} catch (Exception e)
		{
			throw new ServiceException("Check login error: ", e);
		}
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
