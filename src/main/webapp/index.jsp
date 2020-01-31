<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Index, Welcome page</title>
  </head>
<body>
<div align="center" class="p-3 mb-2 bg-light text-dark">
<h2>Welcome to FlightManager Application</h2>

<a href="/home" class="btn btn-warning btn-lg btn-block">Click here to go to the Home Page of Flight Manager Application</a>
	<div align="center">
 		* By: Jasmin Adilovic *</br>
 		* E-mail: adilovic79yahoo.com *</br>
 		* Date: FEB 2020 *</br>
	</div>
	<div align="justify" class="p-3 mb-2 bg-success text-white">
		1. An airport must have a name consisting of exactly three alphabeticalcharacters. No two airports can have the same name.</br>
		2. An airline has a name that must have less than 6 alphabetic characters. No two airlines can have the same name.</br>
		3. Each flight consists of seats organized in rows. Each row is labeled with ("A", "B", "C", "D", "E", "F"). Each row has number of seats in row.</br>
		4. Every flight has an airport,airline origin destination and seats.</br>
	</div>
	<div align="left">
		User can:</br>
 			-Create airport</br>
 			-Create airline</br>
 			-Create flight</br>
 			-Book a seat on a flight</br>
	</div>
		<a href="/hello" class="btn btn-primary btn-lg btn-block">Webapp Hosting Info Servlet</a>
	</div>
</body>
</html>
