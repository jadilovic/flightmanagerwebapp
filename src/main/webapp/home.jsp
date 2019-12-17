<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<div align="center">
<h3>Welcome to Flight Manager Application</h3>
<h5>Please Choose From Options Offered</h5>

<table>
<tr>
	<td>
		<form action="${pageContext.request.contextPath}/airport" method="get">
			<input type="submit" name="option" value="Create Airport" />
		</form>
	</td>
	<td>
		<form action="${pageContext.request.contextPath}/airport" method="post">
			<input type="submit" name="option" value="List Airports" />
		</form>
	</td>	
</tr>
<tr>
	<td>
		<form action="${pageContext.request.contextPath}/airline" method="get">
			<input type="submit" name="option" value="Create Airline" />
		</form>
	</td>
	<td>
		<form action="${pageContext.request.contextPath}/airline" method="post">
			<input type="submit" name="option" value="List Airlines" />
		</form>
	</td>
</tr>
<tr>
	<td>
		<form action="${pageContext.request.contextPath}/flight" method="get">
			<input type="submit" name="option" value="Create Flight" />
		</form>
	</td>
	<td>
		<form action="${pageContext.request.contextPath}/flight" method="get">
			<input type="submit" name="option" value="List Flights" />
		</form>
	</td>
</tr>
<tr>
	<td>
		<form action="${pageContext.request.contextPath}/flight" method="get">
			<input type="submit" name="option" value="Find a Flight" />
		</form>
	</td>
	<td>
		<form action="${pageContext.request.contextPath}/bookflight" method="get">
			<input type="submit" name="option" value="Book a Flight" />
		</form>
	</td>
</tr>
<tr>
	<td>
		<form action="${pageContext.request.contextPath}/">
			<input type="submit" name="option" value="Exit Application" />
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