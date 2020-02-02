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
<title>Airlines List</title>
</head>
<body>
<div align="center" class="p-3 mb-2 bg-light text-dark">
 
	<table>
		<tr>
			<td>
			<a href="/airline" class="btn btn-primary btn-lg btn-block"><i class="fas fa-home"></i> Home</a>
			</td>
			<td>
			<form action="${pageContext.request.contextPath}/airline" method="get">
				<input class="btn btn-primary btn-lg btn-block" type="submit" name="option" value="Create Airline" />
			</form>
			</td>
		</tr>
	</table>
		
	<div class="p-1 mb-2 bg-warning text-dark">
		<h4><i class="fas fa-plane"></i> List of Available Airlines</h4>
	</div>
	

    <table class="table table-striped table-success">
       <tr>
       		<th>Number</th>
          	<th>Airline Name</th>
       </tr>
       <c:forEach items="${airlinesList}" var="airline" >
       <c:set var="num" scope="page" value="${num + 1}" />
          <tr>
          		<td>${num}</td>
             	<td>${airline.name}</td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="/airline" >Home</a>
</div>
</body>
</html>