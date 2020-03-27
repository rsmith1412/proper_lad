<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
	<div class="edit_product">
		<h2>Edit Show</h2>
		<form:form action="/products/${product.id}" method="POST" modelAttribute="product">
		<input type="hidden" name="_method" value="put">
			<p>
		        <form:label path="name">Title:</form:label>
		        <form:input path="name"/>
		        <form:errors style="color:red;" path="name"/>
		    </p>
		    <p>
		        <form:label path="description">Network:</form:label>
		        <form:input path="description"/>
		        <form:errors style="color:red;" path="description"/>
		    </p>
		    <input type="submit" class="btn btn-primary" value="Edit">
		</form:form>
		<form action="/products/${product.id}/delete" method="post">
	    	<input type="hidden" name="_method" value="delete">
	    	<input class="btn btn-danger" type="submit" value="Delete">
	    </form>
	</div>
</body>
</html>