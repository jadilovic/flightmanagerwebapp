<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Airports List</title>
</head>
<body>
<h3>List of Available Airports</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
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
 
    <a href="/airport" >Home</a>
</body>
</html>