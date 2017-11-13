<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${Organization.Name} </title>
</head>

<body>
	<h1>${Organization.Name}</h1>
	<table action="Organization/" method="PUT">
	    <c:forEach items="${Organization}" var="entry">
	    		<tr>
	    			<td>${entry.key}</td><td>${entry.value}</td>
	    		</tr>
		</c:forEach>
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