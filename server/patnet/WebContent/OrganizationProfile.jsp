<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${Organization.getName()} </title>
</head>

<body>
	<h1>${Organization.getName()}</h1>
	
	
	<c:if test="${Organization.getOrganizationId().equals(User.getOrganizationId())}">
    		<a href = "/patnet/Organizations/?for=edit" >Edit</a>
	</c:if>
	
	<table action="Organization/" method="PUT">
		<tr>
			<th>OrganizationId</th>
			<th>Name</th>
			<th>Address</th>
			<th>City</th>
			<th>State</th>
			<th>Phone</th>
			<th>Location</th>
		</tr>
    		<tr>
    			<td>${Organization.getOrganizationId()}</td>
    			<td>${Organization.getName()}</td>
    			<td>${Organization.getAddress()}</td>
    			<td>${Organization.getCity()}</td>
    			<td>${Organization.getState()}</td>
    			<td>${Organization.getPhone()}</td>
    			<td>${Organization.getLocation()}</td>
    		</tr>
	</table>
	
	<h3>Reviews</h3>
	<table>
		<c:forEach items="${Reviews}" var="review">
		    <tr>
	    			<td>${review.getRating()}</td><td>${review.getContent()}</td>
	    		</tr>
    		</c:forEach>
	</table>

</body>
</html>