<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login/Registration</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
	<div class="container">
    	<div class="login">
           	<h2>Login</h2>
  
		    <form:form method="POST" action="/login" modelAttribute="user">
		        <p>
		            <form:label path="email">Email:</form:label>
		            <form:input class="form-control" type="email" id="email" path="email"/>
		        </p>
		        <p>
		            <form:label path="password">Password:</form:label>
		            <form:password class="form-control" path="password"/>
		        </p>
		        <input class="btn prime_btn" type="submit" value="Login"/>
		        <p style="color:red;"><c:out value="${error}" /></p>
		    </form:form>
		    <p>OR</p>
		    <a class="btn second_btn" href="/register">Register</a>
        </div>
	</div>
</body>
</html>