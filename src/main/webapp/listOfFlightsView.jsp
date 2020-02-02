<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://kit.fontawesome.com/326e4a206d.js" crossorigin="anonymous"></script>
<title>Flights List</title>
</head>
<body>
<div align="center"  class="p-3 mb-2 bg-light text-dark">

	<table>
		<tr>
			<td>
			<a href="/flight" class="btn btn-primary btn-lg btn-block"><i class="fas fa-home"></i> Home</a>
			</td>
			<td>
			<form action="${pageContext.request.contextPath}/flight" method="get">
				<input class="btn btn-primary btn-lg btn-block" type="submit" name="option" value="Create Flight" />
			</form>
			</td>
		</tr>
		<tr>
			<td>
				 <form action="${pageContext.request.contextPath}/flight" method="get">
					<input type="hidden" name="message" value="" />
					<input type="hidden" name="option" value="Find a Flight" />
					<input class="btn btn-secondary btn-lg btn-block" type="submit" value="Find a Flight" />
				</form>
			</td>
			<td>
				<form action="${pageContext.request.contextPath}/bookflight" method="get">
					<input type="hidden" name="message" value="" />
					<input type="hidden" name="option" value="Flight Seats" />
					<input class="btn btn-secondary btn-lg btn-block" type="submit" value="List Flight Seats" />
				</form>
			</td>
		</tr>
	</table>
	
	<div class="p-1 mb-2 bg-warning text-dark">
		<h4><i class="fas fa-plane-departure"></i> List of Available Flights</h4>
	</div>
 
    <table class="table table-striped table-success">
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

</div>
</body>
</html>