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
import cs636.shoestore.domain.User;
import cs636.shoestore.service.CustomerService;
import cs636.shoestore.service.ServiceException;

/**
 * Servlet implementation class Registeration
 */
@WebServlet("/Registeration")
public class Registeration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registeration() {
        super();
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        String address = request.getParameter("address");
        
        if (!password.equals(confirmPassword)) {
            // Passwords don't match, show error message
            PrintWriter out = response.getWriter();
            out.println("<h2>Passwords don't match. Please try again.</h2>");
            return;
        }
        
        // Check if email already exists in the database
        boolean emailExists;
		try {
			User usr = customerService.checkEmailExists(email);
			emailExists = usr != null ? true : false;
			
			if (emailExists) {
	            // Email already exists, show error message
	            PrintWriter out = response.getWriter();
	            out.println("<h2>Email already exists. Please use a different email.</h2>");
	            return;
	        }
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        // Insert new user record into the database
        boolean isUserCreated;
		try {
			isUserCreated = customerService.registerUser(name, password, email, address);
			
			if (isUserCreated) {
	            // User created successfully, redirect to login page
	            HttpSession session = request.getSession();
	            session.setAttribute("message", "Registration successful. Please login to continue.");
	            request.getRequestDispatcher("index.jsp").forward(request, response);
	        } else {
	            // User creation failed, show error message
	            PrintWriter out = response.getWriter();
	            out.println("<h2>User creation failed. Please try again.</h2>");
	        }
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}

}
