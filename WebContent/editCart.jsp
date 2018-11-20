<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="business.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>JHU Courses</title>
   	<link rel="stylesheet" href="Styles/form.css">
  </head>
<body>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	<h2>JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR</h2>
	<% User user = (User) session.getAttribute("user"); %>
	<%= user.getName() %>
	<form action="CartServlet" method="post">
		<input type="hidden" name="action" value="compute" >
		<h4>Contact Information</h4>
		<label>Name:</label>
		<input type="text" name="name" value="${user.getName()}" required> <br>
		<label>Email: </label>
		<input type="email" name="email"  value="${user.getEmail()}"required>
		<br>
		<h4>Select Your Courses</h4>

		<select name="courses" size="6" multiple required>
		<c:forEach  var="item" items="${user.getCourses()}" >
			<option value="<c:out value="${item.getName()}"/>" }" ${item.getIsSelected() ? 'selected' : '' }><c:out value="${item.getName()}"/></option>
		</c:forEach>
		</select>
		<br>
		<h4>Employment Status</h4>
		<input type="radio" name="employmentStatus" value="JHU Employee" ${ user.getEmploymentStatus().equals("JHU Employee") ? 'checked' : '' } >JHU Employee
		<input type="radio" name="employmentStatus" value="JHU Student" ${ user.getEmploymentStatus().equals("JHU Student") ? 'checked' : '' } >JHU Student
		<input type="radio" name="employmentStatus" value="Speaker" ${ user.getEmploymentStatus().equals("Speaker") ? 'checked' : '' }>Speaker
		<input type="radio" name="employmentStatus" value="Other" ${ user.getEmploymentStatus().equals("Other") ? 'checked' : '' }>Other
		<br>
		<h4>Additional Fees and Charges</h4>
		<input type="checkbox" name="extras" value="Hotel Accommodation"  ${ user.hasHotel() ? 'checked' : '' } >Hotel Accommodation<br>
		<input type="checkbox" name="extras" value="Parking Permit" ${ user.hasParking() ? 'checked' : '' }>Parking Permit
		<br>
		<br>
		<input type="submit" value="Computer Seminar Costs">
		<br>
	</form>
	
</body>
</html>