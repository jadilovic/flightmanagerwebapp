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
<title>Airports List</title>
</head>
<body>
<div align="center" class="p-3 mb-2 bg-light text-dark">
	
	<table>
		<tr>
			<td>
			<a href="/airport" class="btn btn-primary btn-lg btn-block"><i class="fas fa-home"></i> Home</a>
			</td>
			<td>
			<form action="${pageContext.request.contextPath}/airport" method="get">
				<input class="btn btn-primary btn-lg btn-block" type="submit" name="option" value="Create Airport" />
			</form>
			</td>
		</tr>
	</table>
	
	<div class="p-1 mb-2 bg-warning text-dark">
		<h4><i class="fas fa-globe-americas"></i> List of Available Airports</h4>
	</div>

    <table class="table table-striped table-success">
       <tr>
          <th>Airport Name</th>
          <th>Airport City</th>
       </tr>
       <c:forEach items="${airportsList}" var="airport" >
          <tr>
             <td>${airport.name}</td>
             <td>${airport.city}</td>
          </tr>
       </c:forEach>
    </table>
</div>
</body>
</html>