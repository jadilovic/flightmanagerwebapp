package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


		public void bookSeat() throws SQLException {
			System.out.println("On what flight you would like to book a seat? Please enter flight id.");
			// entering flight id to get a flight
			Integer flightId = FlightManager.enterInteger();
			// Checking if given flight exists
			if(FlightManager.flightIdExists(flightId)){
				List<Seat> seats = null;
				// getting all seats for the flight
				seats = getAllFlightSeats(flightId);
				System.out.println("     SeatID Row Seat# Available FlightID");
				// creating list of id's for each seat on the flight
				List<Integer> listOfSeatsIds = new ArrayList<Integer>();
				// adding all id's to the list
				for(Seat seat: seats){
					listOfSeatsIds.add(seat.getSeatID());
					// showing all seats to the user on the screen
					printSeat(seat);
				}
				System.out.println("Please choose from available seats. Enter seat id.");
				// entering seat id
				int seatId = FlightManager.enterInteger();
				if(listOfSeatsIds.contains(seatId)){
					if(getSeatById(seatId).isAvailable() == true){
						// create an SELECT SQL query
						String query = "UPDATE seats SET available = ? WHERE seatID = ?";
							PreparedStatement statement = connection.prepareStatement(query);
							// fill in the placeholders/parameters
							statement.setBoolean(1, false);
							statement.setInt(2, seatId);
							// execute the query
							statement.executeUpdate();
							// printing booked seat
							printSeat(getSeatById(seatId));
							System.out.println("was booked");
						}
					} else {
						System.out.println("Selected seat is not available");
					}
				} else {
					System.out.println("There is no seat with given seat id");
				}
					System.out.println("There is no flight with given flight id");
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
