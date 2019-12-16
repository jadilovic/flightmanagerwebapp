<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flights List</title>
</head>
<body>
<div align="center" >
<h3>List of Available Flights</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1">
       <tr>
          <th>Flight ID</th>
          <th>Flight Name</th>
          <th>Origin</th>
          <th>Destination</th>
          <th>Airport</th>
          <th>Airline</th>
       </tr>
       <c:forEach items="${flightsList}" var="flight" >
          <tr>
             <td>${flight.id}</td>
             <td>${flight.flight_name}</td>
             <td>${flight.origin}</td>
             <td>${flight.destination}</td>
             <td>${flight.airport}</td>
             <td>${flight.airline}</td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="/flight" >Home</a>
</div>
</body>
</html>