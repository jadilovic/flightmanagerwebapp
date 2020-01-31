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
<title>Home Page</title>
</head>
<body>
<div align="center" class="p-3 mb-2 bg-light text-dark">
	<div class="p-3 mb-2 bg-warning text-dark">
		<h3>Flight Manager Application</h3>
	</div>
	<div class="p-3 mb-2 bg-success text-dark">
		<h4>Please Choose From Options Offered</h4>
	</div>

<table>
<tr>
	<td>
		<form action="${pageContext.request.contextPath}/airport" method="get">
			<input class="btn btn-primary btn-lg btn-block" type="submit" name="option" value="Create Airport" />
		</form>
	</td>
	<td>
		<form action="${pageContext.request.contextPath}/airport" method="post">
			<input class="btn btn-primary btn-lg btn-block" type="submit" name="option" value="List Airports" />
		</form>
	</td>	
</tr>
<tr>
	<td>
		<form action="${pageContext.request.contextPath}/airline" method="get">
			<input class="btn btn-secondary btn-lg btn-block" type="submit" name="option" value="Create Airline" />
		</form>
	</td>
	<td>
		<form action="${pageContext.request.contextPath}/airline" method="post">
			<input class="btn btn-secondary btn-lg btn-block" type="submit" name="option" value="List Airlines" />
		</form>
	</td>
</tr>
<tr>
	<td>
		<form action="${pageContext.request.contextPath}/flight" method="get">
			<input class="btn btn-primary btn-lg btn-block" type="submit" name="option" value="Create Flight" />
		</form>
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
			<input class="btn btn-secondary btn-lg btn-block" type="submit" name="option" value="Find a Flight" />
		</form>
	</td>
	<td>
		<form action="${pageContext.request.contextPath}/bookflight" method="get">
			<input class="btn btn-secondary btn-lg btn-block" type="submit" name="option" value="Flight Seats" />
		</form>
	</td>
</tr>
<tr>
	<td>
		<form action="${pageContext.request.contextPath}/bookflight" method="get">
			<input class="btn btn-warning btn-lg btn-block" type="submit" name="option" value="Book a Seat" />
		</form>
	</td>
	<td>
		<form action="${pageContext.request.contextPath}/">
			<input class="btn btn-danger btn-lg btn-block" type="submit" name="option" value="Exit Application" />
		</form>
	</td>
</tr>
</table>

<c:if test="${param.message != null}">
<p><%= request.getAttribute("message") %></p>
</c:if>
</div>
</body>


</html>