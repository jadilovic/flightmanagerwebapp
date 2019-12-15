<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Airlines List</title>
</head>
<body>
<div align="center" >
<h3>List of Available Airlines</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1">
       <tr>
          <th>Airline Name</th>
       </tr>
       <c:forEach items="${airlinesList}" var="airline" >
          <tr>
             <td>${airline.name}</td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="/airline" >Home</a>
</div>
</body>
</html>