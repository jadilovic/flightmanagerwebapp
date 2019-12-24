<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Seats</title>
</head>
<body>
<div align="center" >
<h3>Flight Seats Page</h3>
<h5>Please enter flight ID to Show All Seats of That Flight</h5>

	<form action="${pageContext.request.contextPath}/bookflight" method="post">
		<input type="hidden" name="message" value="" />
		<input type="hidden" name="option" value="${param.option}" />
		<p>Flight ID:<input type="text" name="flightID" /></p>
		<p><input type="submit" value="SUBMIT" /></p>
	</form>
	
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