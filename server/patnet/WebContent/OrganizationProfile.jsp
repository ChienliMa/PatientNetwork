<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${Organization.getName()} </title>
</head>

<body>
	<h1>${Organization.getName()}</h1>
	
	
	<c:if test="${Organization.getOrganizationId().equals(User.getOrganizationId())}">
    		<a href = "/patnet/Organizations/?for=edit" >Edit</a>
    		<a data-confirm="Are you sure?" data-method="delete" href="/patnet/Organizations" rel="nofollow">Delete</a>
    		
	</c:if>
	
	<table>
	<tbody>
			<tr>	<th>OrganizationId</th><td>${Organization.getOrganizationId()}</td></tr>
			<tr><th>Name</th><td>${Organization.getName()}</td></tr>
			<tr><th>Address</th><td>${Organization.getAddress()}</td></tr>
			<tr><th>City</th><td>${Organization.getCity()}</td></tr>
			<tr><th>State</th><td>${Organization.getState()}</td></tr>
			<tr><th>Phone</th><td>${Organization.getPhone()}</td></tr>
			<tr><th>Location</th><td>${Organization.getLocation()}</td></tr>
	</tbody>
	</table>
	
	<h3>Reviews</h3>
	<c:forEach items="${Reviews}" var="review">
		<table>
			<tr><th>UserName</th><td>${review.getUserName()}</td></tr>
			<tr><th>Rating</th><td>${review.getRating()}</td></tr>
			<tr><th>Content</th><td>${review.getContent()}</td></tr>
		</table>
		<c:if test="${User.getUsername().equals(review.getUserName())}">
			<a data-confirm="Are you sure?" data-method="delete" href="/patnet/OrganizationReviews">Delete</a>
		</c:if>
 	</c:forEach>
 	
</body>
</html>