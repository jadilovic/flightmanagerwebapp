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
	 <style> 
            .gfg { 
                border-collapse:separate; 
                border-spacing:0 15px; 
            } 
        </style> 
<title>Create Flight</title>
</head>
<body>
<div align="center" class="p-3 mb-2 bg-light text-dark">

	<div class="p-1 mb-2 bg-warning text-dark">
		<h4><i class="fas fa-globe-europe"></i> Create Flight</h4>
	</div>
	<div class="p-1 mb-2 bg-success text-white">
		<h5><i class="fas fa-plane-arrival"></i> Please complete fields to create an flight</h5>
	</div>
	
	<c:if test="${param.message != null}">
		<div class="p-2 bg-danger text-white" >
			<%= request.getAttribute("message") %>
		</div>
	</c:if>

<form action="${pageContext.request.contextPath}/flight" method="post">
	<input type="hidden" name="message" value="" />
	<input type="hidden" name="option" value="${param.option}" />
	<table class="gfg">
		<tr  class="p-2 bg-primary text-white">
			<td>
				<i class="fas fa-passport fa-lg"></i>
			</td>
			<td>
				<strong> Flight ID: </strong>
			</td>
			<td>
				<input type="text" name="flightId" />
			</td>
		</tr>
		
		<tr class="p-2 bg-secondary text-white">
			<td>
				<i class="fas fa-plane fa-lg"></i>
			</td>
			<td>
				<strong> Flight name: </strong>
			</td>
			<td>
				<input type="text" name="flightName" />
			</td>
		</tr>
		<tr class="p-2 bg-primary text-white">
			<td>
				<i class="fas fa-city fa-lg"></i>
			</td>
			<td>
				<strong> Origin: </strong>
			</td>
			<td>
				<input type="text" name="origin" />
			</td>
		</tr>
		<tr class="p-2 bg-secondary text-white">
			<td>
				<i class="fas fa-map-marked-alt fa-lg"></i>
			</td>
			<td>
				<strong> Destination: </strong>
			</td>
			<td>
				<input type="text" name="destination" />
			</td>
		</tr>
		<tr class="p-2 bg-primary text-white">
			<td>
				<i class="fas fa-plane-arrival fa-lg"></i>
			</td>
			<td>
				<strong> Airport: </strong>
			</td>
			<td>
				<input type="text" name="airport" />
			</td>
		</tr>
		<tr class="p-2 bg-secondary text-white">
			<td>
				<i class="fas fa-paper-plane fa-lg"></i>
			</td>
			<td>
				<strong> Airline: </strong>
			</td>
			<td>
				<input type="text" name="airline" />
			</td>
		</tr>
		<tr class="p-2 bg-primary text-white">
			<td>
				<i class="fas fa-chair fa-lg"></i>
			</td>
			<td>
				<strong> Seats per row: </strong>
			</td>
			<td>
				<input type="text" name="seatsPerRow" />
			</td>
		</tr>
	</table>
	<input class="btn btn-secondary btn-lg btn-block" type="submit" value="Submit" />
	</form>

	<i class="fas fa-list"></i>
	<form action="${pageContext.request.contextPath}/flight" method="post">
		<input class="btn btn-info btn-lg btn-block" type="submit" name="option" value="List Flights" />
	</form>
	<p></p>
	<a href="/airport" class="btn btn-warning btn-lg btn-block"><i class="fas fa-home"></i> Home</a>
</div>
</body>
</html>