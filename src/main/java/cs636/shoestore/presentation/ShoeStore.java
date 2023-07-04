package cs636.shoestore.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs636.shoestore.config.ShoeStoreConfig;
import cs636.shoestore.domain.Shoe;
import cs636.shoestore.service.CustomerService;
import cs636.shoestore.service.ServiceException;

@WebServlet("/shoes")
public class ShoeStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService;
       
    public ShoeStore() {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Shoe> shoes;
		try {
			shoes = customerService.getShoeList(null);
			System.out.println(shoes);
			request.setAttribute("shoes", shoes);
			request.getRequestDispatcher("menu.jsp").forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		shoes.add(new Shoe(1, "Nike Air Max", 99.99, "The Nike Air Max is a classic running shoe that provides comfort and style."));
//		shoes.add(new Shoe(2, "Adidas Ultraboost", 129.99, "The Adidas Ultraboost is a popular running shoe known for its comfort and durability."));
//		shoes.add(new Shoe(3, "Puma Suede Classic", 64.99, "The Puma Suede Classic is a stylish sneaker that can be dressed up or down."));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
