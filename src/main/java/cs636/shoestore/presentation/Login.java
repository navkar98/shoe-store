package cs636.shoestore.presentation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.shoestore.config.ShoeStoreConfig;
import cs636.shoestore.service.CustomerService;
import cs636.shoestore.service.ServiceException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
        
        try {
        	System.out.println("Inside constructor");
			ShoeStoreConfig.configureServices();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        customerService = ShoeStoreConfig.getCustomerService(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Check if email and password match a record in the database
        boolean isValidUser;
		try {
			String userId = customerService.getUserIdByEmail(email);
			isValidUser = customerService.checkAdminLogin(email, password);
			HttpSession session = request.getSession();
			
			if (isValidUser) {
				customerService.checkAdminLogin(email, password);
				
	            // User is authenticated, redirect to home page
				request.setAttribute("user_id", userId);
	            request.getRequestDispatcher("shoes").forward(request, response);
	        } else {
	            // Authentication failed, show error message
	            PrintWriter out = response.getWriter();
	            out.println("<h2>Login failed. Please check your email and password.</h2>");
	        }
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
