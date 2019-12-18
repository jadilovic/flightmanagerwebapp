<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Seats</title>
</head>
<body>
<div align="center" >
<h3>List of Seats on a Selected Flight</h3>

    <table border="1">
    <c:set var = "listSize" scope = "page" value = "${param.listSize}"/>
    <c:if test="${listSize > 0}">
    	<tr>
          <th>Seat ID</th>
          <th>Row</th>
          <th>Seat Number</th>
          <th>Available</th>
          <th>Flight ID</th>
       </tr>
    </c:if>

       <c:forEach items="${flightSeats}" var="seat" >
          <tr>
             <td>${seat.seatID}</td>
             <td>${seat.row}</td>
             <td>${seat.seatNumber}</td>
             <td>${seat.available}</td>
             <td>${seat.flightID}</td>
          </tr>
       </c:forEach>
    </table>
    
    <c:if test="${param.message != null}">
		<div style="background-color:red" >
			<p><%= request.getAttribute("message") %></p>
		</div>
	</c:if>

	 <form action="${pageContext.request.contextPath}/bookflight" method="get">
		<input type="hidden" name="message" value="" />
		<input type="hidden" name="option" value="Book a Seat" />
		<p><input type="submit" value="Book a Seat" /></p>
	</form>
	
		 <form action="${pageContext.request.contextPath}/flight" method="get">
		<input type="hidden" name="message" value="" />
		<input type="hidden" name="option" value="Find a Flight" />
		<p><input type="submit" value="Find a Flight" /></p>
	</form>
	
    <a href="/bookflight" >Home</a>
</div>
</body>
</html>