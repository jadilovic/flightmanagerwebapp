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
    	req.setAttribute("message", message);
    	
    	if(option != null) {
        	if(option.equals("List Seats")) {
        		doPost(req, resp);
        	} else if(option.equals("Flight Seats")) {
                req.getRequestDispatcher("/flightseats.jsp").forward(req, resp);
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
		String flightID = request.getParameter("flightID");
		String seatID = request.getParameter("seatID");
		String page = "";
    	String message = null;
    	request.setAttribute("message", message);
		System.out.println("DoPOST Seat Servlet");
		
		Connection conn = MyUtils.getStoredConnection(request);
		FlightManager flg = new FlightManager(conn);
		AirportManager air = new AirportManager(conn);
		AirlineManager lin = new AirlineManager(conn);
		SeatManager seat = new SeatManager(conn);
		List<Seat> listOfSeats = new ArrayList<Seat>();
		Seat bookedSeat = new Seat();
			
		if(option.equals("Flight Seats")) {
			if(flightID.equals("")) {
				message = "No flight ID entered. Please try again";
				request.setAttribute("message", message);
				page = "/flightseats.jsp";
			} else {
				try {
					listOfSeats = seat.getFlightSeats(flightID);
					request.setAttribute("flightSeats", listOfSeats);
					request.setAttribute("listSize", listOfSeats.size());
					request.setAttribute("message", seat.getMessage());
					if(listOfSeats.size() == 0)
						page = "/flightseats.jsp";
					else
						page = "/listOfSeatsView.jsp";
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if(option.equals("List Seats")) {
			try {
				listOfSeats = seat.getAllSeats();
				request.setAttribute("flightSeats", listOfSeats);
				request.setAttribute("listSize", listOfSeats.size());
				request.setAttribute("message", seat.getMessage());
				page = "/listOfSeatsAllFlightsView.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else if(option.equals("Book a Seat")) {
			try {
				bookedSeat = seat.bookSeat(flightID, seatID);
				request.setAttribute("seat", bookedSeat);
				request.setAttribute("message", seat.getMessage());
				page = "/bookaseat.jsp";
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
