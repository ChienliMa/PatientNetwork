<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update a Review</title>
</head>
<body>
	<h1>Update Review</h1>
 	<form action="/patnet/OrganizationReviews" method="post" id="OrganizationReview">
        <p>
        	<input type="hidden"  name="method" name="method" value="edit"/>
        	<input type="hidden" name="ReviewId" value="${Review.getReviewId()}"/>
            <input type="hidden" name="Username" value="${Review.getUsername()}"/>
            <input type="hidden" name="OrganizationId" value="${Review.getOrganizationId()}"/>
        </p>
         <p>
             <label>Rating</label>
             <input id="Rating" type="text" name="Rating" value="${Review.getRating()}">
         </p>
         <p>
             <label>Content</label>
             <input id="Content" type="text" name="Content" value="${Review.getContent()}">
         </p>
    </form>
    <button type="submit" form="OrganizationReview" value="Submit">Submit</button>
</body>
</html>