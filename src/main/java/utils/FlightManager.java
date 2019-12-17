package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.Airport;
import beans.Flight;


public class FlightManager {
	
	// AIRPORT INTERFACE IMPLEMENTATION
	// connect to the database
	private static Connection connection;
	
	// message from the database
	private String message;
	
	public FlightManager(Connection conn) {
		FlightManager.connection = conn;
	}
	
		public List<Flight> getAllFlights() throws SQLException {
			// create list of all flights
			List<Flight> listOfFlights = new ArrayList<Flight>();
			// create an SELECT SQL query
			String query = "SELECT * FROM flight";
			// create a new ResultSet
			ResultSet rs = null;
				Statement statement = connection.createStatement();
				// execute the query
				rs = statement.executeQuery(query);
				// add airports to the arrayList
				while (rs.next()) {
					listOfFlights.add(new Flight(rs.getInt("id"), rs.getString("flight_name"), rs.getString("origin"), 
							rs.getString("destination"), rs.getString("airport"), rs.getString("airline")));
				}
				return listOfFlights;
			}
		

		public static Flight getFlight(Integer id) throws SQLException {
			// new flight
			Flight flight = new Flight();
			// create an SELECT SQL query
			String query = "SELECT * FROM flight WHERE id = ?";
			// create a new ResultSet
			ResultSet rs = null;
				PreparedStatement pstatement = connection.prepareStatement(query);
				// fill in the placeholders/parameters
				pstatement.setInt(1, id);
				// execute the query
				rs = pstatement.executeQuery();
				// set the cursor
				if (rs.next()) {
					// populate flight
					flight = new Flight(rs.getInt("id"), rs.getString("flight_name"), rs.getString("origin"), 
							rs.getString("destination"), rs.getString("airport"), rs.getString("airline"));
					// close the ResultSet
					rs.close();
				}
				return flight;	
			}

		public List<Flight> findFlight(String origin, String destination) throws SQLException {
			// create list of all flights
			List<Flight> listOfFlights = new ArrayList<Flight>();
			// create an SELECT SQL query
			String query = "SELECT * FROM flight WHERE origin = ? AND destination = ?";
			// create a new ResultSet
			ResultSet rs = null;
			PreparedStatement statement = connection.prepareStatement(query);
			// fill in the placeholders/parameters
			statement.setString(1, origin);
			statement.setString(2, destination);

			// execute the query
			rs = statement.executeQuery();
				// add airports to the arrayList
				while (rs.next()) {
					listOfFlights.add(new Flight(rs.getInt("id"), rs.getString("flight_name"), rs.getString("origin"), 
							rs.getString("destination"), rs.getString("airport"), rs.getString("airline")));
				}
				if(listOfFlights.size() > 0) {
					message = "Available flights";
				} else {
					message = "No available flights with given origin and destination"
							+ ". Please try again or return to the Home page.";
				}
				return listOfFlights;
			}

		public void addFlight(String flightId2, String flightName, String origin, String destination, 
				String airport, String airline, String seatsPerRow) throws SQLException {
			// create an SELECT SQL query
			String query = "INSERT INTO flight (id, flight_name, origin, destination, airport, airline) "
							+ "VALUES (?, ?, ?, ?, ?, ?)";
			int flightId = 0;
			
			// giving unique id to the new flight
			//System.out.print("Enter unique ID of the flight: ");
		if(flightId2.matches("[0-9]+") && !flightId2.equals("")){
			flightId = Integer.parseInt(flightId2);
			
			// checking if flight ID already exists
			if(!flightIdExists(flightId)){
				//System.out.print("Enter flight name: ");
				//String flightName = enterString();
				// checking if flight name already exists
				if(!flightNameExists(flightName.toUpperCase()) && !flightName.equals("")){
					//System.out.print("Enter origin: ");
					//String origin = enterString();
					// checking if origin location exists
					if(locationExists(origin) && !origin.equals("")){
						//System.out.print("Enter destination: ");
						//String destination = enterString();
						// checking if destination location exists
						if(locationExists(destination)){
							//System.out.print("Enter airport: ");
							//String airport = enterString();
							// checking if airport exists and that origin valid for the airport
							Airport enteredAirport = AirportManager.getAirport(airport.toUpperCase());
							if(enteredAirport != null && originOfAirport(enteredAirport, origin)){
								//System.out.print("Enter airline: ");
								//String airline = enterString();
								// checking if airline exists
								if(AirlineManager.getAirline(airline.toUpperCase()) != null){
									//System.out.println("Please enter number of seats per row");
									if(seatsPerRow.matches("[0-9]+") && !seatsPerRow.equals("")){
									int numSeatsInRow = Integer.parseInt(seatsPerRow);
									// creating seats for the new created flight
									SeatManager.createSeats(numSeatsInRow, flightId);
										PreparedStatement statement = connection.prepareStatement(query);
										// fill in the placeholders/parameters
										statement.setInt(1, flightId);
										statement.setString(2, flightName.toUpperCase());
										statement.setString(3, origin);
										statement.setString(4, destination);
										statement.setString(5, airport);
										statement.setString(6, airline);
									// execute the query
									statement.executeUpdate();
						message = "Flight with id " + flightId + " and flight name " + flightName + " "
													+ " origin " + origin + " and destination " + destination + " "
													+ "added to the database.";
									} else {
										message = "Entered number of seats per row is not a number or is empty";
									}
										} else {
											message = "Entered airline does not exist or airline is empty.";
										}
									} else {
										message = "Entered airport does not exist or the orgine does not match";
									}
								} else {
									message = "Entered destination does not exist or destination is empty.";
								}
							} else {
								message = "Entered origin does not exist or origin is empty";
							}
						} else {
							message = "Entered flight name already exist or flight name is empty";
						}
					} else {
						message = "Entered flight ID already exist";
					} 
				} else {
					message = "Entered flight ID is not valid or not entry was made. It must be a number.";
				}
			}
		
		
		// checking if flight id exists
		static boolean flightIdExists(int flightId) throws SQLException {
			Flight flight = getFlight(flightId);
			if(flight.getId() == null)
				return false;
			else
			return true;
		}

		// checking if origin is same as the the city of the airport
		private boolean originOfAirport(Airport enteredAirport, String origin) {
			if(enteredAirport.getCity().equals(origin))
				return true;
			else{
				System.out.println("Entered airport and origin do not match");
				return false;
			}
		}

		// Checking if the flight name already exists
		private boolean flightNameExists(String flightName) throws SQLException {
			List<Flight> flights = getAllFlights();
			for(Flight flight: flights){
				if(flight.getFlight_name().equals(flightName))
					return true;
			}
			return false;
		}

		// checking if origins and destinations exist in the list of airports
		private boolean locationExists(String location) throws SQLException {
			List<Airport> airports = AirportManager.getAllAirports();
			for(Airport airport: airports){
				if(location.equals(airport.getCity()))
				return true;
			}
			return false;
		}

		// entering integer
		static Integer enterInteger() {
			Scanner input = new Scanner(System.in);
			Integer value = input.nextInt();
			// input.close();
			return value;
		}

		// entering string
		private String enterString() {
			// new Scanner
			java.util.Scanner input = new java.util.Scanner(System.in);
				String text = input.next();
			// close the scanner
			//input.close();
			return text;
		}
		
		// finding a flight with given origin and destination
		public void enterOriginAndDestination(String origin, String destination) throws SQLException {
			// System.out.println("Please enter origin of the flight");
			// String origin = enterString();
			// System.out.println("Please enter destination of the flight");
			// String destination = enterString();
			int count = 0;
			for(Flight flight: getAllFlights()){
				if(flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)){
					System.out.println("Available flight:");
					printFlight(flight);
					count++;
				} else {
					continue;
				}
			}
			if(count == 0)
			System.out.println("There are ne flight available with entered origin and destination");
		}

		public void printFlight(Flight flight) {
			if (flight != null) {
				System.out.println("Flight name: " + flight.getFlight_name() + ", flight id: " + flight.getId() + ""
						+ ", airline: " + flight.getAirline() + ", origin: " + flight.getOrigin() + ""
								+ " and destination " + flight.getDestination());
			} else {
				System.out.println("No airport to print.");
			}
		}
		
		public void printAllFlights(List<Flight> flights) throws SQLException {
			for(Flight flight: flights)
				printFlight(flight);
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}
}
