package cs636.shoestore.presentation;

import java.io.IOException;
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
 * Servlet implementation class BuyShoe
 */
@WebServlet("/buy")
public class BuyShoe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyShoe() {
        super();
        try {
        	System.out.println("Inside constructor");
			ShoeStoreConfig.configureServices();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        customerService = ShoeStoreConfig.getCustomerService(); 
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		String shoeId = request.getParameter("id");
		String userId = request.getParameter("user_id");
		String price = request.getParameter("price");
		
		System.out.println(shoeId + " " + userId + " " + price);
		try {
			customerService.processCart(shoeId, null, price, userId);
			HttpSession session = request.getSession();
            session.setAttribute("message", "Shoe have been bought. Logging you out.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
