<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User profile</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
	$(document).ready(function() {

		var usertype = $("#usertype").val();
		console.log(usertype);

		if (usertype === "ORDINARY") {
			$("#ordinaryDiv").show();
			$("#organizationDiv").hide();
			$("#physicianDiv").hide();
		} else if (usertype === "ORGANIZATION") {
			$("#ordinaryDiv").hide();
			$("#organizationDiv").show();
			$("#physicianDiv").hide();
		} else {
			$("#ordinaryDiv").hide();
			$("#organizationDiv").hide();
			$("#physicianDiv").show();
		}

	});
</script>

</head>
<body>
	<h1>User profile</h1>
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

	<input id="usertype" name="usertype" type="hidden"
		value="${user.getType()}">

	<!-- <div id="userFind">
		<a href="findusers">Find User</a>
	</div> -->

	<p>
		<a
			href="userupdate?username=<c:out value="${user.getUserName()}"/>
			&usertype=${user.getType()}">
			Update your information </a>
	</p>
	<br />

	<div id="ordinaryDiv" style="display: none;">
		<div id="orgsearch">
			<a href="userdelete"> Find Organization </a>
		</div>

		<br />

		<div id="physearch">
			<a href="userdelete"> Find Physician </a>
		</div>
	</div>

	<div id="physicianDiv" style="display: none;">
		<div id="physearch">
			<a href="userdelete"> View your Physician profile </a>
		</div>
	</div>

	<div id="organizationDiv" style="display: none;">
		<div id="orgProfile">
			<a href="orgprofile"> View your Organization profile </a>
		</div>
	</div>


</body>
</html>