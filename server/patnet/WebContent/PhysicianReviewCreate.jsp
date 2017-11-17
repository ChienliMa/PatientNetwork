<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create a Physician Review</title>
</head>
<body>
	<h1>Create Physician Review</h1>
	<form action="physicianreviewcreate" method="post">
		<p>
			<label for="reviewid">ReviewId</label>
			<input id="reviewid" name="reviewid" value="">
		</p>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="">
		</p>
		<p>
			<label for="created">Created</label>
			<input id="created" name="created" value="">
		</p>
		<p>
			<label for="rating">Rating</label>
			<input id="rating" name="rating" value="">
		</p>
		<p>
			<label for="content">Content</label>
			<input id="content" name="content" value="">
		</p>
		<p>
			<label for="providerid">ProviderId</label>
			<input id="providerid" name="providerid" value="">
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