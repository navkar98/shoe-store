package cs636.shoestore.config;

/**
 * @author Navkar
 *
 * Configure the service objects, shut them down
 * 
 */

import java.io.PrintWriter;
import java.io.StringWriter;

import cs636.shoestore.dao.DbDAO;
import cs636.shoestore.dao.ShoeDAO;
import cs636.shoestore.dao.UserDAO;
import cs636.shoestore.dao.ShoeCartDAO;
import cs636.shoestore.service.CustomerService;

public class ShoeStoreConfig {
	// the service objects in use, representing all lower layers to the app
	private static CustomerService customerService;
	
	// the lower-level service objects--
	private static UserDAO userDAO;
	private static ShoeDAO shoeDAO;
	private static ShoeCartDAO shoeCartDAO;
	private static DbDAO dbDAO; // contains Connection

	// set up service API, data access objects
	public static void configureServices()
			throws Exception {
		// Configure service layer and DAO objects, all singletons--
		// DAOs first, then service objects, for DI (dependency injection),
		// a form of IoC (inversion of control)
		// The service objects get what they need at creation-time
		// This is known as "constructor injection"
		try {
			dbDAO = new DbDAO();
			userDAO = new UserDAO(dbDAO);
			shoeDAO = new ShoeDAO(dbDAO);
			shoeCartDAO = new ShoeCartDAO(dbDAO);
			customerService = new CustomerService(dbDAO, userDAO, shoeCartDAO, shoeDAO);
		} catch (Exception e) {
			System.out.println(exceptionReport(e));
			// e.printStackTrace(); // causes lots of output
			System.out.println("Problem with contacting DB: " + e);
			throw (e); // rethrow to notify caller
		}
	}

	// Compose an exception report
	// and return the string for callers to use
	public static String exceptionReport(Exception e) {
		String message = e.toString(); // exception name + message
		if (e.getCause() != null) {
			message += "\n  cause: " + e.getCause();
			if (e.getCause().getCause() != null) {
				message += "\n    cause's cause: " + e.getCause().getCause();
			}
		}
		message += "\n Stack Trace: "
						+ exceptionStackTraceString(e);
		return message;
	}

	private static String exceptionStackTraceString(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	// When the app exits, the shutdown happens automatically
	// For other cases, call this to free up the JDBC Connection
	public static void shutdownServices() throws Exception {
		dbDAO.close(); // close JDBC connection
	}

	public static CustomerService getCustomerService() {
		return customerService;
	}
}
