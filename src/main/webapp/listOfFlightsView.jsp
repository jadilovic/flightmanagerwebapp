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
 
    <table border="1">
    <c:set var = "listSize" scope = "page" value = "${param.listSize}"/>
    <c:if test="${listSize > 0}">
    	<tr>
          <th>Flight ID</th>
          <th>Flight Name</th>
          <th>Origin</th>
          <th>Destination</th>
          <th>Airport</th>
          <th>Airline</th>
       </tr>
    </c:if>

       <c:forEach items="${flightsList}" var="flight" >
          <tr>
             <td>${flight.id}</td>
             <td>${flight.flight_name}</td>
             <td>${flight.origin}</td>
             <td>${flight.destination}</td>
             <td>${flight.airport_name}</td>
             <td>${flight.airline_name}</td>
          </tr>
       </c:forEach>
    </table>
    
    <c:if test="${param.message != null}">
	<div style="background-color:red" >
			<p><%= request.getAttribute("message") %></p>
	</div>
	</c:if>

	 <form action="${pageContext.request.contextPath}/flight" method="get">
		<input type="hidden" name="message" value="" />
		<input type="hidden" name="option" value="Find a Flight" />
		<p><input type="submit" value="Find a Flight" /></p>
	</form>
	
	<form action="${pageContext.request.contextPath}/bookflight" method="get">
		<input type="hidden" name="message" value="" />
		<input type="hidden" name="option" value="Flight Seats" />
		<p><input type="submit" value="List Flight Seats" /></p>
	</form>
	
    <a href="/flight" >Home</a>
</div>
</body>
</html>