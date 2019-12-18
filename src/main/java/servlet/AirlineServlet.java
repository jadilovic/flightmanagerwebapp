package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Airline;
import utils.MyUtils;

import utils.AirlineManager;

@WebServlet(
        name = "AirlineServlet", 
        urlPatterns = {"/airline"}
    )
public class AirlineServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirlineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	System.out.println("DoGET Airline Servlet");
    	String option = req.getParameter("option");
    	
    	if(option != null) {
        	if(option.equals("List Airlines")) {
        		doPost(req, resp);
        	} else if(option.equals("Create Airline")) {
                req.getRequestDispatcher("/createairline.jsp").forward(req, resp);
        	} 
    	} else {
        	req.getRequestDispatcher("/home.jsp").forward(req, resp);
    	}
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		String airlineName = request.getParameter("airlineName");
		String page = "";
		System.out.println("DoPOST Airline Servlet");
		
		Connection conn = MyUtils.getStoredConnection(request);
		AirlineManager create = new AirlineManager(conn);
			
		if(option.equals("Create Airline")) {
				try {
					create.addAirline(airlineName);
					request.setAttribute("message", create.getMessage());
					page = "/createairline.jsp";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		else if(option.equals("List Airlines")) {
			List<Airline> listOfAirports = new ArrayList<Airline>();
			try {
				listOfAirports = create.getAllAirlines();
				request.setAttribute("airlinesList", listOfAirports);
				request.setAttribute("message", create.getMessage());
				page = "/listOfAirlinesView.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			page = "/home";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}
}
