package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Airline;


public class AirlineManager {
	
	// AIRPORT INTERFACE IMPLEMENTATION
	// connect to the database
	private static Connection connection;
	
	// message from the database
	private String message;
	
	public AirlineManager(Connection conn) {
		this.connection = conn;
	}

	public List<Airline> getAllAirlines() throws SQLException {
		// create list of all airlines
		List<Airline> listOfAirlines = new ArrayList<Airline>();
		// create an SELECT SQL query
		String query = "SELECT * FROM airline";
		// create a new ResultSet
		ResultSet rs = null;
				Statement statement = connection.createStatement();
			// execute the query
			rs = statement.executeQuery(query);
			// add airlines to the list
			while (rs.next()) {
				listOfAirlines.add(new Airline(rs.getString("airline_name")));
			}
			return listOfAirlines;
		}



	public static Airline getAirline(String airlineName) throws SQLException {
		// airline object
		Airline airline = null;
		// create an SELECT SQL query
		String query = "SELECT * FROM airline WHERE airline_name = ?";
		// create a new ResultSet
		ResultSet rs = null;
			PreparedStatement pstatement = connection.prepareStatement(query);
			// fill in the placeholders /parameters
			pstatement.setString(1, airlineName);
			// execute the query
			rs = pstatement.executeQuery();
			// set the cursor
			if (rs.next()) {
				// populate airline
				airline = new Airline(rs.getString("airline_name"));
				// close the ResultSet
				rs.close();
			}
			return airline;
		}

	public void addAirline(String airlineName) throws SQLException {
		// create an SELECT SQL query
		String query = "INSERT INTO airline (airline_name) VALUES (?)";
		// creating new airline
		//System.out.print("Enter airline name: ");
		//String name = enterString();
		// checking airline name and if it exists
		if(validAirlineName(airlineName) && !airlineExists(airlineName)){
				PreparedStatement statement = connection.prepareStatement(query);
				// fill in the placeholders/parameters
				statement.setString(1, airlineName.toUpperCase());
				// execute the query
				statement.executeUpdate();
				message = "Airline " + airlineName.toUpperCase() + " added to the database.";
			}
		}

	// checking if airline exists in the database already
	private boolean airlineExists(String name) throws SQLException {
		if(getAirline(name) != null){
			message = "Entered Airline name already exists. Please try again.";
			return true;
		}
		return false;
	}

	// checking if the given airline name is less than 5 characters and all alphabetic characters
	private boolean validAirlineName(String name) throws SQLException {
		if(name.length() > 5){
			message = "Length of the Airline name is greater than 5 characters. Please try again.";
			return false;
		}
		else if(!onlyAlphabets(name)){
			message = "Entered Airline name does not contain all alphabets. Please try again.";
			return false;
		} else
		return true;
	}

	// Checking if given name is all alphabets
	private static boolean onlyAlphabets(String name) {
        return ((name != null) 
                && (!name.equals("")) 
                && (name.matches("^[a-zA-Z]*$")));
	}

	public void printAirline(Airline airline) {
		if (airline != null) {
			System.out.println("Airline: " + airline.getName());
		} else {
			System.out.println("No airline to print.");
		}
	}
	
	public void printAllAirlines(List<Airline> airlines) throws SQLException {
		for(Airline airline: airlines)
			printAirline(airline);
	}
	
	
		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}
}
