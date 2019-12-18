<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
         
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Airport</title>
</head>
<body>
<div align="center" >
<h3>Create Airport Page</h3>
<h5>Please enter data to create an airport</h5>
<form action="${pageContext.request.contextPath}/airport" method="post">
	<input type="hidden" name="message" value="" />
	<input type="hidden" name="option" value="${param.option}" />
	<p>Airport name:</p><input type="text" name="airportName" /><br/>
	<p>Airport city:</p><input type="text" name="airportCity" /><br/>
	<p></p><input type="submit" value="SUBMIT" />
</form>

	<c:if test="${param.message != null}">
		<div style="background-color:red" >
			<p><%= request.getAttribute("message") %></p>
		</div>
	</c:if>

	<p><a href="/airport" >Home</a></p>
</div>
</body>
</html>