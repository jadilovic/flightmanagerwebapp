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
import beans.Flight;
import utils.MyUtils;
import utils.SeatManager;
import utils.AirlineManager;
import utils.AirportManager;
import utils.FlightManager;

@WebServlet(
        name = "FlightServlet", 
        urlPatterns = {"/flight"}
    )
public class FlightServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(req);
		FlightManager create = new FlightManager(conn);
		List<Flight> listOfFlights = new ArrayList<Flight>();
    	System.out.println("DoGET Flight Servlet");
    	String option = req.getParameter("option");
    	String message = "";
    	
    	if(option != null) {
        	if(option.equals("List Flights")) {
        		doPost(req, resp);
        	} else if(option.equals("Create Flight")) {
				try {
					listOfFlights = create.getAllFlights();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				req.setAttribute("flightsList", listOfFlights);
                req.getRequestDispatcher("/createflight.jsp").forward(req, resp);
        	} else if(option.equals("Find a Flight")) {
        		req.setAttribute("message", message);
        		req.getRequestDispatcher("/findaflight.jsp").forward(req, resp);
        	}
    	} else {
        	req.getRequestDispatcher("/home.jsp").forward(req, resp);
    	}
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		String flightId = request.getParameter("flightId");
		String flightName = request.getParameter("flightName");
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		String airport = request.getParameter("airport");
		String airline = request.getParameter("airline");
		String seatsPerRow = request.getParameter("seatsPerRow");
		String page = "";
		System.out.println("DoPOST Flight Servlet");
		
		Connection conn = MyUtils.getStoredConnection(request);
		FlightManager create = new FlightManager(conn);
		AirportManager air = new AirportManager(conn);
		AirlineManager lin = new AirlineManager(conn);
		SeatManager seat = new SeatManager(conn);
		List<Flight> listOfFlights = new ArrayList<Flight>();
			
		if(option.equals("Create Flight")) {
				try {
					create.addFlight(flightId, flightName, origin, destination, airport, airline, seatsPerRow);
					request.setAttribute("message", create.getMessage());
					page = "/createflight.jsp";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		else if(option.equals("List Flights")) {
			try {
				listOfFlights = create.getAllFlights();
				request.setAttribute("flightsList", listOfFlights);
				request.setAttribute("listSize", listOfFlights.size());
				request.setAttribute("message", create.getMessage());
				page = "/listOfFlightsView.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else if(option.equals("Find a Flight")) {
			try {
				listOfFlights = create.findFlight(origin, destination);
				request.setAttribute("flightsList", listOfFlights);
				request.setAttribute("listSize", listOfFlights.size());
				request.setAttribute("message", create.getMessage());
				page = "/listOfFlightsView.jsp";
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
