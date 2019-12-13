package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.CertificateCreateRule;

import utils.AirportManager;

@WebServlet(
        name = "AirportServlet", 
        urlPatterns = {"/airport"}
    )
public class AirportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/createairport.jsp").forward(req, resp);
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		String airportName = request.getParameter("airportName");
		String airportCity = request.getParameter("airportCity");
		String message = "";
		AirportManager create = new AirportManager();
		
		if(option.equals("Create Airport")) {
			try {
				create.addAirport(airportName, airportCity);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		message = create.getMessage();
		PrintWriter out = response.getWriter();
		out.println(message);
		
	}
}
