<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TopLad</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../css/customerStyles.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
	<header>
		<c:choose>
			<c:when test="${session.id}">
				<p>Hello {user.id}</p>
				<a href="/logout">Logout</a>
			</c:when>
			<c:otherwise>
				<a href="/login">Login</a>
			</c:otherwise>
		</c:choose>
	</header>
	<nav>
		<h3>Top Lad</h3>
		<a href="#"></a>  
		<div class="right-icons">
			<span>0<i class="material-icons">shopping_cart</i></span>
		</div>
	</nav>
	<div class="footwear">
		
	</div>
</body>
</html>