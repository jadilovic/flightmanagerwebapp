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

import beans.Airport;
import utils.MyUtils;

import utils.AirportManager;

@WebServlet(
        name = "AirportServlet", 
        urlPatterns = {"/airport"}
    )
public class AirportServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	System.out.println("DoGET Airport Servlet");
    	String option = req.getParameter("option");
    	
    	if(option != null) {
        	if(option.equals("List Airports")) {
        		doPost(req, resp);
        	} else if(option.equals("Create Airport")) {
                req.getRequestDispatcher("/createairport.jsp").forward(req, resp);
        	} 
    	}
    	req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		String airportName = request.getParameter("airportName");
		String airportCity = request.getParameter("airportCity");
		String page = "";
		System.out.println("DoPOST Airport Servlet");
		
		Connection conn = MyUtils.getStoredConnection(request);
		AirportManager create = new AirportManager(conn);
			
		if(option.equals("Create Airport")) {
				try {
					create.addAirport(airportName, airportCity);
					request.setAttribute("message", create.getMessage());
					page = "/home.jsp";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		else if(option.equals("List Airports")) {
			List<Airport> listOfAirports = new ArrayList<>();
			try {
				listOfAirports = create.getAllAirports();
				request.setAttribute("airportsList", listOfAirports);
				request.setAttribute("message", create.getMessage());
				page = "/listOfAirportsView.jsp";
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
