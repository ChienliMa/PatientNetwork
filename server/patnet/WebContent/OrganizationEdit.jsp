<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${Organization.Name} </title>
</head>

<body>
	<h1>${Organization.Name}</h1>
	<form action="/patnet/Organizations" method="post" id="OrganizationForm">
	  	<p>
	  		<input type="hidden" name="OrganizationId" value="${Organization.OrganizationId}"/>
	  	</p>
	
    		<p>
	    		<label>Name</label>
	    		<input id="Name" name="Name" value="${Organization.Name}">
    		</p>
    		<p>
	    		<label>Address</label>
	    		<input id="Address" name="Address" value="${Organization.Address}">
    		</p>
    		<p>
	    		<label>City</label>
	    		<input id="City" name="City" value="${Organization.City}">
    		</p>
    		<p>
	    		<label>State</label>
	    		<input id="State" name="State" value="${Organization.State}">
    		</p>
    		<p>
	    		<label>ZipCode</label>
	    		<input id="ZipCode" name="ZipCode" value="${Organization.ZipCode}">
    		</p>
    		<p>
	    		<label>Phone</label>
	    		<input id="Phone" name="Phone" value="${Organization.Phone}">
    		</p>
	</form>
	
	<button type="submit" form="OrganizationForm" value="Submit">Submit</button>
	
</body>