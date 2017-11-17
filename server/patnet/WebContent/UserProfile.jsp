<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User profile</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		console.log("toggle");

		var usertype = $("#usertype").val();

		console.log(usertype);

		if (usertype === "ORDINARY") {
			console.log("manager ORDINARY");
			$("#organizationProfile").hide();
			$("#physicianProfile").hide();
		} else if (usertype === "ORGANIZATION") {
			console.log("manager ORGANIZATION");
			$("#organizationProfile").show();
			$("#physicianProfile").hide();
		} else {
			console.log("manager phy");
			$("#organizationProfile").hide();
			$("#physicianProfile").show();
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

	<input id="usertype" name="usertype" value="${user.getType()}"
		type="hidden">

	<div>
		<div id="organizationProfile" style="display: none;">
			<a
				href="OrganizationProfile?OrganizationId=<c:out value="${user.getOrganizationId()}"/>">
				View your Organization Profile </a>
		</div>

		<br />

		<div id="physicianProfile" style="display: none;">
			<a
				href="OrganizationProfile?PhysicianId=<c:out value="${user.getPhysicianId()}"/>">
				#TODO View your Physician profile </a>
		</div>

	</div>

	<p>
		<a
			href="userupdate?username=<c:out value="${user.getUserName()}"/>
			&usertype=${user.getType()}">
			Update your user information </a>
	</p>
	<br />

	<div id="ordinaryDiv">
		<div id="orgsearch">
			<a href="FindOrganizations.jsp"> Find Organization </a>
		</div>

		<br />

		<div id="physearch">
			<a href="FindPhysicians.jsp"> Find Physician </a>
		</div>
	</div>

</body>
</html>