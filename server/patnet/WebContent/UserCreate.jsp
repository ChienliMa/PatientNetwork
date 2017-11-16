<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create a User</title>

<script>
	$(document).ready(function() {
		$('#myform').submit(function(e) {
			console.log("here");

			var usertype = $("#usertype").val();
			console.log(usertype);

			/* alert("before submit"); */

			$("#hiddenField").val(usertype);
			/* e.preventDefault(); */
		});

		$("#usertype").change(function(e) {

			console.log("toggle");

			var usertype = $("#usertype").val();

			console.log(usertype);

			if (usertype === "ORDINARY") {
				console.log("manager ORDINARY");
				$("#orgdiv").hide();
				$("#phydiv").hide();
			} else if (usertype === "ORGANIZATION") {
				console.log("manager ORGANIZATION");
				$("#orgdiv").show();
				$("#phydiv").hide();
			} else {
				console.log("manager phy");
				$("#orgdiv").hide();
				$("#phydiv").show();
			}
		});

	});
</script>
</head>
<body>
	<h1>Create User</h1>
	<form action="usercreate" method="post" id="myform">
		<p>
			<label for="username">UserName</label> <input id="username"
				name="username" value="">
		</p>
		<p>
			<label for="password">Password</label> <input id="password"
				name="password" value="" type="password">
		</p>
		<p>
			<label> Select user type </label> <select name="select" id="usertype">
				<option value="ORDINARY" selected>Ordinary User</option>
				<option value="ORGANIZATION">Organization User</option>
				<option value="PHYSICIAN">Physician User</option>
			</select>
		</p>
		<!-- 		<p>
			<label for="organizationId">OrganizationId</label> <input
				id="organizationId" name="organizationId" value="">
		</p> -->


		<div id="orgdiv" style="display: none;">
			<label for="organizationName">Organization Name</label> <input
				id="organizationName" name="organizationName" value="">
		</div>


		<div id="phydiv" style="display: none;">
			<label for="physicianId">PhysicianId </label> <input id="physicianId"
				name="physicianId" value="">
		</div>

		<p>
			<label for="firstname">FirstName</label> <input id="firstname"
				name="firstname" value="">
		</p>
		<p>
			<label for="lastname">LastName</label> <input id="lastname"
				name="lastname" value="">
		</p>
		<input type="hidden" name="hiddenField" id="hiddenField"
			value="ORDINARY">
		<p>
			<input type="submit">
		</p>
	</form>
	<br />
	<br />
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>