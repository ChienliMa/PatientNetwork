<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update User</title>
</head>
<body>
	<h1>Update User</h1>
	<form action="userupdate" method="post" id="myform">
		<p>
			Hello <label for="username">${messages.username}</label>
		</p>
		<input id="username" name="username" type="hidden"
			value="${messages.username}">
		<p>
			<label for="password">Password</label> <input id="password"
				name="password" value="" type="password">
		</p>

		<!-- 		
		<p>
			<label for="organizationId">OrganizationId</label> <input
				id="organizationId" name="organizationId" value="">
		</p>
		<p>
			<label for="physicianId">PhysicianId</label> <input id="physicianId"
				name="physicianId" value="">
		</p> 
		-->

		<p>
			<label for="firstname">FirstName</label> <input id="firstname"
				name="firstname" value="">
		</p>
		<p>
			<label for="lastname">LastName</label> <input id="lastname"
				name="lastname" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br />
	<br />
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>

	<p>
		<span id="failureMessage"><b>${messages.failure}</b></span>
	</p>


</body>


</html>