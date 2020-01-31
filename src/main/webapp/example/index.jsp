<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Welcome to FlightManager Application</h2>

<a href="/home">Click here to go to the Home Page of Flight Manager Application</a>
<p></p>
<a href="/hello">Click here to go to the Hello Servlet</a>

<p>
 Trying to run applet
 </p>
 
 <jsp:plugin type="applet" code="ButtonMoveApplet.class" codebase="/example"
  width="400" height="400">
  <jsp:fallback>
   <p>Unable to load applet</p>
   </jsp:fallback>
</jsp:plugin>

</body>
</html>
