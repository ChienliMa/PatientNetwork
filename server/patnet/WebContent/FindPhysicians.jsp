<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find a Physician</title>
</head>
<body>
	<form action="findphysicians" method="post">
		<h1>Search for a Physician by ProviderId</h1>
		<p>
			<label for="providerid">ProviderId</label>
			<input id="providerid" name="providerid" value="${fn:escapeXml(param.providerid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="physicianCreate"><a href="physiciancreate">Create a Physician</a></div>
	<div id="physicianReviewCreate"><a href="physicianreviewcreate">Create a Physician Review</a></div>
	<br/>
	<h1>Matching Physician</h1>
        <table border="1">
            <tr>
                <th>ProviderId</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>MiddleName</th>
                <th>Credential</th>
                <th>Gender</th>
                <th>StreetAddress1</th>
                <th>StreetAddress2</th>
                <th>ZipCode</th>
                <th>State</th>
                <th>PrimarySpecialty</th>
                <th>SecondarySpecialties</th>
                <th>Delete a Physician</th>
                <th>update a Physician</th>
                <th>Review</th>
            </tr>
            
            <tr>
                <td><c:out value="${physician.getProviderId()}" /></td>
                <td><c:out value="${physician.getFirstName()}" /></td>
                <td><c:out value="${physician.getLastName()}" /></td>
                <td><c:out value="${physician.getMiddleName()}" /></td>
                <td><c:out value="${physician.getCredential()}" /></td>
                <td><c:out value="${physician.getGender()}" /></td>
                <td><c:out value="${physician.getStreetAddress1()}" /></td>
                <td><c:out value="${physician.getStreetAddress2()}" /></td>
                <td><c:out value="${physician.getCity()}" /></td>
                <td><c:out value="${physician.getState()}" /></td>
                <td><c:out value="${physician.getPrimarySpecialty()}" /></td>
                <td><c:out value="${physician.getSecondarySpecialties()}" /></td>
                <td><a href="physiciandelete?providerid=<c:out value="${physician.getProviderId()}"/>">Delete</a></td>
                <td><a href="physicianupdate?providerid=<c:out value="${physician.getProviderId()}"/>">Update</a></td>
                <td><a href="physicianreviewdisplay?providerid=<c:out value="${physician.getProviderId()}"/>">Review</a></td>
            </tr>
</body>
</html>