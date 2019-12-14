package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Airport;
import conn.ConnectionUtils;
import conn.MySQLConnUtils;


public class AirportManager {
	
	// AIRPORT INTERFACE IMPLEMENTATION
	// connect to the database
	private Connection connection;
	
	// message from the database
	private String message;
	
	public AirportManager(Connection conn) {
		this.connection = conn;
	}

		
		public List<Airport> getAllAirports() throws SQLException {
			// create list of all Airport objects
			List<Airport> listOfAirports = new ArrayList<Airport>();
			// create an SELECT SQL query
			String query = "SELECT * FROM airport a";
			// create a new ResultSet
			ResultSet rs = null;
			
			PreparedStatement statement = connection.prepareStatement(query);
				// execute the query
				rs = statement.executeQuery(query);
				// add airports to the arrayList
				while (rs.next()) {
					listOfAirports.add(new Airport(rs.getString("airport_name"), rs.getString("airport_city")));
				}
		
			if(listOfAirports.size() > 0)
				message = "List of airports";
			else
				message = "No airports in the database";
			return listOfAirports;
		}

		public Airport getAirport(String airportName) throws SQLException {
			// new airport object
			Airport airport = null;
			// create an SELECT SQL query
			String query = "SELECT * FROM airport a WHERE a.airport_name = ?";
			// create a new ResultSet
			ResultSet rs = null;
			
				PreparedStatement pstatement = connection.prepareStatement(query);
				// fill in the placeholders/parameters
				pstatement.setString(1, airportName);
				// execute the query
				rs = pstatement.executeQuery();
				// set the cursor
				if (rs.next()) {
					// populate airport
					airport = new Airport(rs.getString("airport_name"), rs.getString("airport_city"));
					// close the ResultSet
					rs.close();
				}
				return airport;
			}

		
		public void addAirport(String airportName, String airportCity) throws SQLException {
			System.out.println("Add airport");
			// create an SELECT SQL query
			String query = "INSERT INTO airport (airport_name, airport_city) VALUES (?, ?)";
			
			// giving name for the new airport
			//System.out.print("Enter airport name: ");
			//String name = enterString();
			// giving city for the new airport
			//System.out.print("Enter city: ");
			//String city = enterString();
			
			// checking validity of the name given and if it already exists
			if(validAirportName(airportName) && !airportExists(airportName)){
				
					PreparedStatement statement = connection.prepareStatement(query);
					// fill in the placeholders/parameters
					statement.setString(1, airportName.toUpperCase());
					statement.setString(2, airportCity);
					// execute the query
					statement.executeUpdate();
					//System.out.println("Airport " + name + " in the city " + city + " added to the database.");
					message = "Airport " + airportName.toUpperCase() + " in the city " + airportCity + " added to the database.";
				}
			}
		

		// method to check length of the name and if it contains only alphabets
		private boolean validAirportName(String name) throws SQLException {
			if(name.length() != 3){
				message = "Length of the Airport name '" + name + "' must be exactly 3 characters. Please try again.";
				return false;
			}
			else if(!onlyAlphabets(name)){
				message = "Entered Airport name '" + name + "' does not contain all alphabets. Please try again.";
				return false;
			} else 
			return true;
		}

		// method checking if airport already exists
		private boolean airportExists(String name) throws SQLException {
				if(getAirport(name) != null){
					message = "Entered Airport name '" + name + "' already exists. Please try again.";
					return true;
				}
			return false;
		}

		public void printAirport(Airport airport) {
			if (airport != null) {
				message = "Airport name: " + airport.getName() + ", city: " + airport.getCity();
			} else {
				message = "No airport to print.";
			}
		}
		
		// Checking if given name is all alphabets
		private static boolean onlyAlphabets(String name) {
	        return ((name != null) 
	                && (!name.equals("")) 
	                && (name.matches("^[a-zA-Z]*$")));
		}
		
		public void printAllAirports(List<Airport> airports) throws SQLException {
			for(Airport airport: airports)
				printAirport(airport);
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}
}