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
	<form action="/patnet/Organizations" method="post" id="OrganizationForm">
	  	<p>
	  		<input type="hidden" name="EditPropose" value="${EditPropose}" />
	  		<input type="hidden" name="OrganizationId" value="${Organization.getOrganizationId().toString()}"/>
	  	</p>
	
    		<p>
	    		<label>Name</label>
	    		<input id="Name" name="Name" value="${Organization.getName()}">
    		</p>
    		<p>
	    		<label>Address</label>
	    		<input id="Address" name="Address" value="${Organization.getAddress()}">
    		</p>
    		<p>
	    		<label>City</label>
	    		<input id="City" name="City" value="${Organization.getCity()}">
    		</p>
    		<p>
	    		<label>State</label>
	    		<input id="State" name="State" value="${Organization.getState()}">
    		</p>
    		<p>
	    		<label>ZipCode</label>
	    		<input id="ZipCode" name="ZipCode" value="${Organization.getZipCode()}">
    		</p>
    		<p>
	    		<label>Phone</label>
	    		<input id="Phone" name="Phone" value="${Organization.getPhone()}">
    		</p>
	</form>
	
	<button type="submit" form="OrganizationForm" value="Submit">Submit</button>
	
</body>