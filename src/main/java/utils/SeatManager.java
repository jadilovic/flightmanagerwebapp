package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Seat;


public class SeatManager {

	private static Connection connection;
	
	// message from the database
	private String message;
	
	public SeatManager(Connection conn) {
		SeatManager.connection = conn;
	}
	
	public List<Seat> getAllSeats() throws SQLException {
		// creating list of seats for one particular flight
		List<Seat> listOfSeats = new ArrayList<Seat>();
		// create an SELECT SQL query
		String query = "SELECT * FROM seats";
		// create a new ResultSet
		ResultSet rs = null;

		Statement statement = connection.createStatement();
		// execute the query
		rs = statement.executeQuery(query);
			// add seat to the list of seats
			while (rs.next()) {
				listOfSeats.add(new Seat(rs.getInt("seatID"), rs.getString("rowLetter"), rs.getInt("seat_number"), 
						rs.getBoolean("available"), rs.getInt("flightID")));
			}
			return listOfSeats;
		}
	
		public List<Seat> getAllFlightSeats(Integer flightId) throws SQLException {
			// creating list of seats for one particular flight
			List<Seat> listOfSeats = new ArrayList<Seat>();
			// create an SELECT SQL query
			String query = "SELECT * FROM seats WHERE flightID = ?";
			// create a new ResultSet
			ResultSet rs = null;

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, flightId);
				// execute the query
				rs = statement.executeQuery();
				// add seat to the list of seats
				while (rs.next()) {
					listOfSeats.add(new Seat(rs.getInt("seatID"), rs.getString("rowLetter"), rs.getInt("seat_number"), 
							rs.getBoolean("available"), rs.getInt("flightID")));
				}
				return listOfSeats;
			}

		public Seat getSeatById(Integer id) throws SQLException {
			// Seat object
			Seat seat = null;
			// create an SELECT SQL query
			String query = "SELECT * FROM seats WHERE seatID = ?";
			// create a new ResultSet
			ResultSet rs = null;

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, id);
				// execute the query
				rs = statement.executeQuery();
				// add airports to the arrayList
				while (rs.next()) {
					seat = new Seat(rs.getInt("seatID"), rs.getString("rowLetter"), rs.getInt("seat_number"), 
							rs.getBoolean("available"), rs.getInt("flightID"));
				}
				return seat;
			}

		public Seat getSeatByOther(String row, int seatNum, int flightId) throws SQLException {
			// Seat object
			Seat seat = null;
			// create an SELECT SQL query
			String query = "SELECT * FROM seats WHERE rowLetter = ? AND seat_number = ? AND flightID = ?";
			// create a new ResultSet
			ResultSet rs = null;

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, row);
				statement.setInt(2, seatNum);
				statement.setInt(3, flightId);
				// execute the query
				rs = statement.executeQuery();
				// getting a seat
				while (rs.next()) {
					seat = new Seat(rs.getInt("seatID"), rs.getString("rowLetter"), rs.getInt("seat_number"), 
							rs.getBoolean("available"), rs.getInt("flightID"));
				}
				return seat;
			}


		// creating seats for the new created flight
		public static void createSeats(int numSeatsInRow, Integer flightID) throws SQLException {
			// Insert query
			String query = "INSERT INTO seats (rowLetter, seat_number, available, flightID) "
					+ "VALUES (?, ?, ?, ?)";
			// creating seats based on number of seats in the row and adding it to the flight with given ID
			String [] rowsSQL = {"A", "B", "C", "D", "E", "F"};
			for(int i = 0; i < rowsSQL.length; i++){
				for(int j = 1; j <= numSeatsInRow; j++){
				PreparedStatement pstat = connection.prepareStatement(query);
						pstat.setString(1, rowsSQL[i]);
						pstat.setInt(2, j);
						pstat.setBoolean(3, true);
						pstat.setInt(4, flightID);
						pstat.executeUpdate();
					}
				}
			}

		public List<Seat> getFlightSeats(String flightId) throws SQLException {
			
			int flightIdINT = 0;
			List<Seat> flightSeats = new ArrayList<Seat>();
			
		if(flightId.matches("[0-9]+") && !flightId.equals("")){
			flightIdINT = Integer.parseInt(flightId);
			
			if(FlightManager.flightIdExists(flightIdINT)){

				// getting all seats for the flight
				flightSeats = getAllFlightSeats(flightIdINT);
				//System.out.println("     SeatID Row Seat# Available FlightID");
						} else {
							message = "There is no flight with given flight id";
						} 
					} else {
						message = "Entered flight ID is not a number or it is empty.";
					}	
		return flightSeats;
				} 


		public Seat bookSeat(String flightID, String seatID) throws SQLException {
			// initializing seatID and flightID integers
			int flightIdINT = 0;
			int seatIdINT = 0;
			Seat seat = null;

			// checking if entered flight ID is a number
		if(flightID.matches("[0-9]+") && !flightID.equals("")){
			flightIdINT = Integer.parseInt(flightID);
			
			// checking if entered seat ID is a number
			if(seatID.matches("[0-9]+") && !seatID.equals("")){
				seatIdINT = Integer.parseInt(seatID);
			
				// check if flight ID exists
				if(FlightManager.flightIdExists(flightIdINT)) {
					
					// check if the seat ID exists
					if((getSeatById(seatIdINT)) != null){
						
						// check if the seat is available
						if(getSeatById(seatIdINT).isAvailable() == true){
							
						// create an SELECT SQL query
						String query = "UPDATE seats SET available = ? WHERE seatID = ?";
							PreparedStatement statement = connection.prepareStatement(query);
							// fill in the placeholders/parameters
							statement.setBoolean(1, false);
							statement.setInt(2, seatIdINT);
							// execute the query
							statement.executeUpdate();
							
							seat = getSeatById(seatIdINT);
							// printing booked seat
							message = "Seat with ID number " + seatIdINT + " was booked on the flight with ID: " + flightIdINT;
								} else {
									message = "Selected seat is not available";
								}
							} else {
								message = "There is no seat with given seat id";
							}
						} else {
							message = "There is no flight with given flight id";
						} 
					} else {
						message = "Entered seat ID is not a number or it is empty.";
					}		
				}  else {
					message = "Entered flight ID is not a number or it is empty.";
				}
		return seat;
			}

		
		public void printSeat(Seat seat) {
			System.out.println("Seat: " + seat.getSeatID() + ",    " + seat.getRow() + ""
					+ ",    " + seat.getSeatNumber() + ",    " + seat.isAvailable() + ","
							+ "    " + seat.getFlightID());
		}
		
		
		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}
}
