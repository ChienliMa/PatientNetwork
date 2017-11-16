<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User</title>
</head>
<body>
	<h1>User Profile</h1>
	<p>
		Username : <label for="username"> ${user.getUserName()}</label>
	</p>
	<p>
		Firstname : <label for="firstname">${user.getFirstName()}</label>
	</p>
	<p>
		Lastname : <label for="lastname">${user.getLastName()}</label>
	</p>

	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>

	<div id="userFind">
		<a href="findusers">Find User</a>
	</div>

	<a
		href="userupdate?username=<c:out value="${user.getUserName()}"/>
			&usertype=${user.getType()}">
		Update your information </a>

</body>
</html>