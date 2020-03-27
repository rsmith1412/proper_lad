<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard Orders</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
	<nav>
		<div class="col-md-6 header-row-left">
			<h1>Top Lad</h1>
			<a href="/dashboard/orders">Orders</a>
			<a href="/dashboard/products">Products</a>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-2">
			<a href="/logout">Log Off</a>
		</div>
	</nav>
	<div class="row search-row">
		<form action="/orders/search">
			<input type="hidden" name="action" value="Search">
			<input id="search_bar" type="search" name="search_string" placeholder="Search orders..." >
			<button class="search_button"><i class="icon-search"></i></button>
		</form>
	</div>
	<table class="table table-striped orders_table">
        <thead class="dark">
            <tr>
                <th scope="col">Order ID</th>
                <th scope="col">Name</th>
                <th scope="col">Date</th>
                <th scope="col">Billing Address</th>
                <th scope="col">Total</th>
                <th scope="col">Status</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${orders}" var="o">
            <tr>
                <td><a href="/orders/show/${o.id}"><c:out value="${o.id}"/></a></td>
                <td><c:out value="${o.first_name}"/></td>
                <td><c:out value="${o.billing_address}"/> <c:out value="${o.billing_city}"/> <c:out value="${o.billing_state}"/> <c:out value="${o.billing_zip_code}"/></td>
                <td><c:out value="${o.total_price}"/></td>
                <td>
                	<form:form action="/userscourses" method="POST" modelAttribute="userCourse">
                		<form:input type="hidden" path="user" value="${user.id}" />
	                	<form:input type="hidden" path="course" value="${c.id}" />
	                	<input type="submit" value="Add">
					</form:form>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>