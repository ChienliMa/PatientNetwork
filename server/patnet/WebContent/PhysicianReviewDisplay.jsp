<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Physician Review</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>ReviewId</th>
                <th>UserName</th>
                <th>Created</th>
                <th>Rating</th>
                <th>Content</th>
                <th>Delete a Review</th>
                <th>Update a Review</th>
            </tr>
            <c:forEach items="${physicianReviews}" var="physicianReview" >
                <tr>
                    <td><c:out value="${physicianReview.getReviewId()}" /></td>
                    <td><c:out value="${physicianReview.getUsername()}" /></td>
                    <td><fmt:formatDate value="${physicianReview.getCreated()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                    <td><c:out value="${physicianReview.getRating()}" /></td>
                    <td><c:out value="${physicianReview.getContent()}" /></td>
                    <td><a href="updatephysicianreview?reviewid=<c:out value="${physicianReview.getReviewId()}"/>">Update</a></td>
                    <td><a href="deletephysicianreview?reviewid=<c:out value="${physicianReview.getReviewId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>