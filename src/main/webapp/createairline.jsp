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
<title>Create Airline</title>
</head>
<body>
<div align="center" class="p-3 mb-2 bg-light text-dark">

	<div class="p-1 mb-2 bg-warning text-dark">
		<h4><i class="fas fa-plane"></i> Create Airline</h4>
	</div>
	<div class="p-1 mb-2 bg-success text-white">
		<h5><i class="fas fa-globe"></i> Please enter new airline name</h5>
	</div>

<form action="${pageContext.request.contextPath}/airline" method="post">
	<input type="hidden" name="message" value="" />
	<input type="hidden" name="option" value="${param.option}" />
		<div class="p-2 bg-primary text-white">
			<strong>Airline name: </strong><br>
			<input type="text" name="airlineName" />
		</div>
		
		<div>
			<i class="fas fa-paper-plane"></i>
		</div>
	<input class="btn btn-primary btn-lg btn-block" type="submit" value="Submit" />
</form>

	<c:if test="${param.message != null}">
		<div class="p-2 bg-danger text-white" >
			<%= request.getAttribute("message") %>
		</div>
	</c:if>
	
	<i class="fas fa-list"></i>
	
	<form action="${pageContext.request.contextPath}/airline" method="post">
		<input class="btn btn-info btn-lg btn-block" type="submit" name="option" value="List Airlines" />
	</form>
	<p></p>
	<a href="/airport" class="btn btn-warning btn-lg btn-block"><i class="fas fa-home"></i> Home</a>
</div>
</body>
</html>