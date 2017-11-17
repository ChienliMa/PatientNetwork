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
	</c:if>
	
	<table border="1">
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
	
	<h2>Write Reviews</h2>    
         <form action="/patnet/OrganizationReviews" method="post" id="OrganizationForm">
             <p>
                 <input type="hidden" name="Username" value="${Username}"/>
                 <input type="hidden" name="OrganizationId" value="${Organization.getOrganizationId()}"/>
             </p>
              <p>
                  <label>Rating</label>
                  <input id="Rating" name="Rating" value="${review.getRating()}">
              </p>
              <p>
                  <label>Content</label>
                  <input id="Content" name="Content" value="${review.getContent()}">
              </p>
         </form>
         <button type="submit" form="OrganizationForm" value="Submit">Submit</button>
    
	<h2>Reviews</h2>
	
		<c:forEach items="${Reviews}" var="review">
			<table border="1">
				<tr><th>UserName</th><td>${review.getUsername()}</td></tr>
				<tr><th>Rating</th><td>${review.getRating()}</td></tr>
				<tr><th>Content</th><td>${review.getContent()}</td></tr>
				<c:if test="${Username.equals(review.getUsername())}">
				<tr>
					<th>edit</th>
					<td>
						<a href="/patnet/OrganizationReviews?method=edit&ReviewId=${review.getReviewId()}">Edit</a>
						<a href="/patnet/OrganizationReviews?method=delete&ReviewId=${review.getReviewId()}&OrganizationId=${Organization.getOrganizationId()}">Delete</a>
					</td>
					</tr>
				</c:if>  
			</table>
			</br>
 		</c:forEach>
	

</body>
</html>