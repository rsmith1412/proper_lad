<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
	<div class="container">
		<h2>Create New Account</h2>
        <p style="color:red;"><form:errors path="user.*"/></p>
		    <form:form method="POST" action="/register_user" modelAttribute="user">
		    	<div class="row">
			    	<div class="form-group col-md-6">
			            <form:label path="first_name">First Name: </form:label>
			            <form:input class="form-control" type="text" path="first_name"/>
			        </div>
			        <div class="form-group col-md-6">
			            <form:label path="last_name">Last Name: </form:label>
			            <form:input class="form-control" type="text" path="last_name"/>
			        </div>
			    </div>
			    <div class="row">
				    <div class="form-group col-md-6">
			            <form:label path="street_address">Street Address: </form:label>
			            <form:input class="form-control" type="text" path="street_address"/>
			        </div>
			        <div class="form-group col-md-2">
			            <form:label path="unit">Unit: </form:label>
			            <form:input class="form-control" type="text" path="unit"/>
			        </div>
			        <div class="form-group col-md-4">
			            <form:label path="city">City: </form:label>
		            	<form:input class="form-control" type="text" path="city"/>
			        </div>
			    </div>
		        <div class="row">
		        	<div class="form-group col-md-1">
		        		<form:label path="state">State: </form:label>
		            	<form:select class="form-control" type="text" path="state">
		            		<c:forEach items="${states}" var="s">
	    						<form:option value="${s}">
	    							<c:out value="${s}" />
	    						</form:option>
	    					</c:forEach>
			    		</form:select>
		        	</div>
		        	<div class="form-group col-md-2">
		        		<form:label path="zip_code">Zip Code: </form:label>
		            	<form:input class="form-control" type="text" path="zip_code"/>
		        	</div>
		        	<div class="form-group col-md-5">
		        		<form:label path="country">Country: </form:label>
		            	<form:input class="form-control" type="text" path="country"/>
		        	</div>
		        	<div class="form-group col-md-4">
		        		<form:label path="phone_number">Phone Number: </form:label>
		            	<form:input class="form-control" type="text" path="phone_number"/>
		        	</div>
		        </div>
		        <div class="row">
		        	<div class="form-group col-md-4">
		        		<form:label path="email">Email:</form:label>
		           		<form:input class="form-control" type="email" path="email"/>
		        	</div>
		        	<div class="form-group col-md-4">
		        		<form:label path="password">Password:</form:label>
		            	<form:password class="form-control" path="password"/>
		        	</div>
		        	<div class="form-group col-md-4">
		        		<form:label path="passwordConfirmation">Confirm Password:</form:label>
		            	<form:password class="form-control" path="passwordConfirmation"/>
		        	</div>
		        </div>
		        <div class="row">
		        	<div class="form-group col-md-4">
		        		<input class="btn prime_btn" type="submit" value="Register!"/>
		        	</div>
		        	
		        </div>
		        <p>OR</p>
		        <a class="btn second_btn" href="/">Login</a>
		    </form:form>
	</div>
</body>
</html>