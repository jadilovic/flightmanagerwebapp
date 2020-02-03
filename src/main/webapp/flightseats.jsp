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
<title>Flight Seats</title>
</head>
<body>
<div align="center"  class="p-3 mb-2 bg-light text-dark">

	<div class="p-1 mb-2 bg-warning text-dark">
		<h4><i class="fas fa-chair"></i> Flight Seats</h4>
	</div>
	<div class="p-1 mb-2 bg-success text-white">
		<h5><i class="fas fa-plane"></i> Please enter flight ID to Show All Seats of That Flight</h5>
	</div>

	<form action="${pageContext.request.contextPath}/bookflight" method="post">
		<input type="hidden" name="message" value="" />
		<input type="hidden" name="option" value="${param.option}" />
			
			<div class="p-2 bg-secondary text-white">
				<strong>Flight ID:</strong><br>
				<input type="text" name="flightID" />
			</div>
		<div>
			<i class="fas fa-paper-plane"></i>
		</div>
		
		<input class="btn btn-primary btn-lg btn-block" type="submit" value="SUBMIT" />
	</form>
	
	<c:if test="${param.message != null}">
		<div  class="p-2 bg-danger text-white" >
			<%= request.getAttribute("message") %>
		</div>
	</c:if>

	<i class="fas fa-list"></i>
	
	<table>
		<tr>
			<td>
			<form action="${pageContext.request.contextPath}/bookflight" method="get">
				<input type="hidden" name="message" value="" />
				<input type="hidden" name="option" value="Book a Seat" />
				<input class="btn btn-info btn-lg btn-block" type="submit" value="Book a Seat" />
			</form>
			</td>
			<td>
			<form action="${pageContext.request.contextPath}/flight" method="get">
				<input type="hidden" name="message" value="" />
				<input type="hidden" name="option" value="Find a Flight" />
				<input class="btn btn-info btn-lg btn-block" type="submit" value="Find a Flight" />
			</form>
			</td>
		</tr>
	</table>

	<a href="/bookflight" class="btn btn-warning btn-lg btn-block"><i class="fas fa-home"></i> Home</a>
</div>
</body>
</html>