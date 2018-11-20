<%@page import="business.Course"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="business.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>You've been registered!</title>
   	<link rel="stylesheet" href="Styles/form.css">
</head>
<body>
	<h2>JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR</h2>

<%-- <% 
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String employmentStatus = request.getParameter("employmentStatus");

    String[] courses = request.getParameterValues("courses");
    String[] extras = request.getParameterValues("extras");

   	User user = new User(name, email, courses, employmentStatus, extras);  
    
%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<% User user = (User) session.getAttribute("user"); %>
	<%= user.getName() %>
	<br>
	<br>
	You are registered as <%= user.getEmploymentStatus() %>	
	<br>
	<br>
	Your e-mail confirmation will be sent to: <%= user.getEmail() %>
	<br>
	<table>
		<tr>
			<th align="left">Your Courses</th>
			<th align="left">Cost</th>
		</tr>
	
	<% for(Course course : user.getCourses()) { %>
		<% if(course.getIsSelected()){ %>
		<tr>
			<td><%= course.name %></td>
			<td>$<%= user.getCourseFee() %>.00</td>
			<form action="CartServlet" method="post"> 
				<input type="hidden" name="action" value="remove">
				<input type="hidden" name="course" value="<%=course %>">
				<td><input type="submit" value="Remove"></td>
			</form>
		</tr>
		<% } %>
	<% } %>	
	<% if (user.getExtras() != null){ %>
		<% for(String extra : user.getExtras()) { %>
			<tr>
				<td><%= extra %></td>
				<td>$<%= user.getExtraFee(extra) %>.00</td>
			</tr>
		<% } %>	
	<% } %>	
		
		<tr>
			<td align="right">Total</td>
			<td>$<%= user.getTotalCost() %>.00</td>
		</tr>	
	</table>
	
	<form action="" method="post">
	  <input type="hidden" name="action" value="edit">
	  <input type="submit" value="Edit Information">
	</form>
	<form action="" method="post">
	  <input type="hidden" name="action" value="add">
	  <input type="submit" value="Add More Courses">
	</form>
	<form action="" method="post">
	  <input type="hidden" name="action" value="confirm">
	  <input type="submit" value="Confirm Registration">
	</form>
</body>
</html>

































