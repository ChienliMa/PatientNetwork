<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find an Organization</title>
</head>
<body>
	<form action="findorganizations" method="post">
		<h1>Search for an Organization by City</h1>
		<p>
			<label for="city">City</label>
			<input id="city" name="city" value="${fn:escapeXml(param.city)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="organizationCreate"><a href="organizationcreate">Create Organization</a></div>
	<br/>
	<h1>Matching Organization</h1>
	<table border="1">
	    <tr>
			<th>OrganizationId</th>
			<th>Name</th>
			<th>Address</th>
			<th>City</th>
			<th>State</th>
			<th>ZipCode</th>
			<th>Phone</th>
			<th>Location</th>
	    </tr>
	
		<c:forEach items="${organizations}" var="organization" >
		<tr>
			<td><c:out value="${organization.getOrganizationId()}" /></td>
			<td><c:out value="${organization.getName()}" /></td>
			<td><c:out value="${organization.getAddress()}" /></td>
			<td><c:out value="${organization.getCity()}" /></td>
			<td><c:out value="${organization.getState()}" /></td>
			<td><c:out value="${organization.getZipCode()}" /></td>
			<td><c:out value="${organization.getPhone()}" /></td>
			<td><c:out value="${organization.getLocation()}" /></td>
		</tr>

		</c:forEach>
	</table>
</body>
</html>