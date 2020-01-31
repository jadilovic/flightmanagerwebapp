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
<title>Create Flight</title>
</head>
<body>
<div align="center" >
<h3>Create Flight Page</h3>
<h5>Please enter data to create an flight</h5>
<form action="${pageContext.request.contextPath}/flight" method="post">
	<input type="hidden" name="message" value="" />
	<input type="hidden" name="option" value="${param.option}" />
	<p>Flight ID:<input type="text" name="flightId" /></p>
	<p>Flight name:<input type="text" name="flightName" /></p>
	<p>Origin:<input type="text" name="origin" /></p>
	<p>Destination:<input type="text" name="destination" /></p>
	<p>Airport:<input type="text" name="airport" /></p>
	<p>Airline:<input type="text" name="airline" /></p>
	<p>Seats per row:<input type="text" name="seatsPerRow" /></p>
	<p><input type="submit" value="SUBMIT" /></p>
	
	<c:if test="${param.message != null}">
	<div style="background-color:red" >
			<p><%= request.getAttribute("message") %></p>
	</div>

	<a href="/flight" >Home</a>
</c:if>
</form>
</div>
</body>
</html>