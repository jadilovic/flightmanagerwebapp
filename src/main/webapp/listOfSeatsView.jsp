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
<title>List of Seats</title>
</head>
<body>
<div align="center" class="p-3 mb-2 bg-light text-dark">

	<table>
		<tr>
			<td>
			<a href="/flight" class="btn btn-primary btn-lg btn-block"><i class="fas fa-home"></i> Home</a>
			</td>
			<td>
			<form action="${pageContext.request.contextPath}/flight" method="get">
				<input class="btn btn-primary btn-lg btn-block" type="submit" name="option" value="List Flights" />
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
					<input type="hidden" name="option" value="Book a Seat" />
					<input class="btn btn-secondary btn-lg btn-block" type="submit" value="Book a Seat" />
				</form>
			</td>
		</tr>
	</table>

	<div class="p-1 mb-2 bg-warning text-dark">
		<h4><i class="fas fa-chair"></i> List of Seats on a Selected Flight</h4>
	</div>

    <table class="table table-striped table-success">
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
		<div  class="p-2 bg-danger text-white" >
			<%= request.getAttribute("message") %>
		</div>
	</c:if>

	<table>
		<tr>
			<td>
				<form action="${pageContext.request.contextPath}/bookflight" method="get">
				<input type="hidden" name="message" value="" />
				<input type="hidden" name="option" value="Book a Seat" />
				<input class="btn btn-secondary btn-lg btn-block" type="submit" value="Book a Seat" />
				</form>
			</td>
			<td>
				<form action="${pageContext.request.contextPath}/flight" method="get">
				<input type="hidden" name="message" value="" />
				<input type="hidden" name="option" value="Find a Flight" />
				<input class="btn btn-secondary btn-lg btn-block" type="submit" value="Find a Flight" />
				</form>
			</td>
		</tr>
	</table>
    <a href="/bookflight" class="btn btn-primary btn-lg btn-block"><i class="fas fa-home"></i>Home</a>
</div>
</body>
</html>