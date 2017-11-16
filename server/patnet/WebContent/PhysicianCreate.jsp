<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create a Physician</title>
</head>
<body>
	<h1>Create Physician</h1>
	<form action="physiciancreate" method="post">
		<p>
			<label for="providerid">ProviderId</label>
			<input id="providerid" name="providerid" value="">
		</p>
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="">
		</p>
		<p>
			<label for="lastname">LastName</label>
			<input id="lastname" name="lastname" value="">
		</p>
		<p>
			<label for="middlename">MiddleName</label>
			<input id="middlename" name="middlename" value="">
		</p>
		<p>
			<label for="credential">Credential</label>
			<input id="credential" name="credential" value="">
		</p>
		<p>
			<label for="gender">Gender</label>
			<input id="gender" name="gender" value="">
		</p>
		<p>
			<label for="streetaddress1">StreetAddress1</label>
			<input id="streetaddress1" name="streetaddress1" value="">
		</p>
		<p>
			<label for="streetaddress2">StreetAddress2</label>
			<input id="streetaddress2" name="streetaddress2" value="">
		</p>
		<p>
			<label for="city">City</label>
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="zipCode">ZipCode</label>
			<input id="zipcode" name="zipcode" value="">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="">
		</p>
		<p>
			<label for="primaryspecialty">PrimarySpecialty</label>
			<input id="primaryspecialty" name="primaryspecialty" value="">
		</p>
		<p>
			<label for="secondaryspecialties">SecondarySpecialties</label>
			<input id="secondaryspecialties" name="secondaryspecialties" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>