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
import beans.Seat;
import utils.MyUtils;
import utils.SeatManager;
import utils.AirlineManager;
import utils.AirportManager;
import utils.FlightManager;

@WebServlet(
        name = "SeatServlet", 
        urlPatterns = {"/bookflight"}
    )
public class SeatServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	System.out.println("DoGET Seat Servlet");
    	String option = req.getParameter("option");
    	String message = "";
    	
    	if(option != null) {
        	if(option.equals("List Seats")) {
        		doPost(req, resp);
        	} else if(option.equals("Book a Flight")) {
                req.getRequestDispatcher("/bookaflight.jsp").forward(req, resp);
        	} else if(option.equals("Book a Seat")) {
        		req.setAttribute("message", message);
        		req.getRequestDispatcher("/bookaseat.jsp").forward(req, resp);
        	}
    	} else {
        	req.getRequestDispatcher("/home.jsp").forward(req, resp);
    	}
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		String flightId = request.getParameter("flightID");
		String page = "";
		System.out.println("DoPOST Seat Servlet");
		
		Connection conn = MyUtils.getStoredConnection(request);
		FlightManager flg = new FlightManager(conn);
		AirportManager air = new AirportManager(conn);
		AirlineManager lin = new AirlineManager(conn);
		SeatManager flight = new SeatManager(conn);
		List<Seat> listOfSeats = new ArrayList<Seat>();
			
		if(option.equals("Book a Flight")) {
				try {
					listOfSeats = flight.bookFlight(flightId);
					request.setAttribute("flightSeats", listOfSeats);
					request.setAttribute("listSize", listOfSeats.size());
					request.setAttribute("message", flight.getMessage());
					page = "/listOfSeatsView.jsp";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		else if(option.equals("List Seats")) {
			try {
				listOfSeats = flight.getAllSeats();
				request.setAttribute("flightSeats", listOfSeats);
				request.setAttribute("listSize", listOfSeats.size());
				request.setAttribute("message", flight.getMessage());
				page = "/listOfSeatsView.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else if(option.equals("Find a Seat")) {
			//listOfFlights = create.findFlight(origin, destination);
			//request.setAttribute("flightsList", listOfFlights);
			//request.setAttribute("listSize", listOfFlights.size());
			request.setAttribute("message", "Find Seat option");
			page = "/listOfSeatsView.jsp";
		} else {
			page = "/home";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}
}
