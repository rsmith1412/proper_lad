<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${product.name}" /></title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../css/customerStyles.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
	<nav>
		<h3>Top Lad</h3>
		<h3><i class="material-icons">shopping_cart</i> (<c:out value="${cart.itemCount}"></c:out>)</h3>
	</nav>
	<a href="/products/{category}/{category.id}/{page}">Back To Products</a>
	<h2><c:out value="${product.name}" /></h2>
	<div class="row">
		<div class="prod_imgs col-md-5">
			<img class="mainImg" src="<c:out value="${img.mainImgUrl}"></c:out>" />
			<c:forEach items="${images}" var="img">
				<img class="prodImg col-2" src="<c:out value="${img.url}" />" />
			</c:forEach>
		</div>
		<div class="desc_panel col-md-6">
			<p><c:out value="${product.description}" /></p>
			<p>$<c:out value="${product.price}" /></p>
			<form:form action="/addToCart/{product.id}" method="POST" modelAttribute="cart">
				<div class="row">
					<div class="form-group col-md-1">
		        		<label path="size">Size: </label>
		            	<select class="form-control" type="text" path="size">
		            		<c:forEach items="${sizes}" var="s">
	    						<option value="${s}">
	    							<c:out value="${s}" />
	    						</option>
	    					</c:forEach>
			    		</select>
		        	</div>
				</div>
				<input type="hidden" path="product" value="${product.id}" />
				<input type="submit" class="addToCart" value="Add To Cart">
			</form:form>
		</div>
	</div>
</body>
</html>