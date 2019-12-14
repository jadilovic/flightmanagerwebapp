package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.CertificateCreateRule;

import conn.ConnectionUtils;
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
        req.getRequestDispatcher("/createairport.jsp").forward(req, resp);
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		String airportName = request.getParameter("airportName");
		String airportCity = request.getParameter("airportCity");
		String message = "";
		
		 Connection conn = MyUtils.getStoredConnection(request);
		AirportManager create = new AirportManager(conn);
			
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
