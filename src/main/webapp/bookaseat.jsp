<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book a Seat</title>
</head>
<body>
<div align="center" >
<h3>Book a Seat Page</h3>
<h5>Please enter flight ID and seat ID to book a seat</h5>
	<form action="${pageContext.request.contextPath}/bookflight" method="post">
		<input type="hidden" name="message" value="" />
		<input type="hidden" name="option" value="${param.option}" />
		<p>Flight ID:<input type="text" name="flightID" /></p>
		<p>Seat ID:<input type="text" name="seatID" /></p>
		<p><input type="submit" value="SUBMIT" /></p>
	</form>
	
	<c:if test="${param.message != null}">
		<div style="background-color:red" >
			<p><%= request.getAttribute("message") %></p>
		</div>
	</c:if>

	 <form action="${pageContext.request.contextPath}/bookflight" method="get">
		<input type="hidden" name="message" value="" />
		<input type="hidden" name="option" value="Flight Seats" />
		<p><input type="submit" value="Book a Seat at Another Flight" /></p>
	</form>
	
	<a href="/bookflight" >Home</a>
	
</div>
</body>
</html>