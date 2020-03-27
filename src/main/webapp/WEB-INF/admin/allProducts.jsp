<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard Products</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="../../js/modal.js" type="text/javascript"></script>
</head>
<body>
	<!-- <div class="modal fade new_prod_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
			      My modal
			    </div>
			</div>
		</div> -->
	<nav>
		<div class="col-md-6 header-row-left">
			<h1>Top Lad</h1>
			<a href="/dashboard/orders">Orders</a>
			<a href="/dashboard/products">Products</a>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-2 header-row-right">
			<p>Hello <c:out value="${user.first_name}"></c:out></p>
			<a href="/logout">Log Off</a>
		</div>
	</nav>
	<div class="row search-row">
		<form action="/products/search">
			<input type="hidden" name="action" value="Search">
			<input id="search_bar" type="search" name="search_string" placeholder="Search products...">
			<button><i class="fa fa-search" aria-hidden="true">Search</i></button>
		</form>
		<button id="new_product" type="button" data-toggle="modal" class="btn btn-primary" data-target="#new_prod_modal">Add New Product</button>
		<div class="modal fade" id="new_prod_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
			    	<div class="modal-header">
			    		<h4 class="modal-title">Create New Product</h4>
			    		<button type="button" class="close" data-dismiss="modal">&times;</button>
			    	</div>
			    	<div class="modal-body">
			    		<p style="color:red;"><form:errors path="product.*"/></p>
			    		<form:form action="/create_product" method="POST" modelAttribute="product" enctype="multipart/form-data">
			    			<div class="form-row">
				    			<div class="form-group col-8">
				    				<form:label path="name">Product Name: </form:label>
				    				<form:input class="form-control" path="name" />
				    				<form:errors style="color:red;" path="name" />
				    			</div>
				    			<div class="form-group col-4">
				    				<form:label path="brand">Brand: </form:label>
				    				<form:input class="form-control" path="brand" />
				    				<form:errors style="color:red;" path="brand" />
				    			</div>
			    			</div>
			    			<div class="form-group">
			    				<form:label path="description">Description: </form:label>
			    				<form:textarea class="form-control" path="description" type="text" />
			    				<form:errors style="color:red;" path="description" />
			    			</div>
			    			<div class="form-row">
				    			<div class="form-group col-6">
				    				<form:label path="price">Price: </form:label>
				    				<form:input class="form-control" path="price" />
				    				<form:errors style="color:red;" path="price" />
				    			</div>
				    			<div class="form-group col-6">
				    				<form:label path="inventory_count">Inventory Count: </form:label>
				    				<form:input class="form-control" path="inventory_count" />
				    				<form:errors style="color:red;" path="inventory_count" />
				    			</div>
			    			</div>
			    			<div class="form-row">
				    			<div class="form-group col-6">
				    				<label for="existingCategory">Categories: </label>
				    				<select class="form-control" name="existingCategory">
				    					<c:forEach items="${categories}" var="c">
				    						<option class="cat_class" value="${c.id}">
				    							<c:out value="${c.category_name}" />
				    						</option>
				    					</c:forEach>
				    				</select>
				    				<%-- <form:errors style="color:red;" path="category" /> --%>
				    			</div>
				    			<div class="form-group col-6">
				    				<label for="newCategory">Or Add a New Category: </label>
				    				<input class="form-control" name="newCategory" />
				    			</div>
			    			</div>
			    			<div class="form-group">
			    				<label for="imageUrl">Image upload: </label>
			    				<input type="file"  name="imageUrl" multiple/>
			    				<%-- <form:errors style="color:red;" path="imageUrl" /> --%>
			    			</div>
			    			<input type="submit" class="btn btn-primary" value="Create Product">
			    		</form:form>
			    	</div>
			    </div>
			</div>
		</div>
	</div>
	<table class="table table-striped product_table">
        <thead class="light">
            <tr>
                <th scope="col">Picture</th>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Inventory Count</th>
                <th scope="col">Quantity Sold</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${products}" var="p">
            <tr>
                <td><a href="/products/${p.id}"><img src="<c:out value="${p.images[0].url}"></c:out>" height="50" width="50"></a></td>
                <td><c:out value="${p.id}"/></td>
                <td><a href="/products/${p.id}"><c:out value="${p.name}"/></a></td>
                <td><c:out value="${p.inventory_count}"/></td>
                <td><c:out value="${p.quantity_sold}"/></td>
                <td class="tableActions">
                	<a class="btn btn-outline-primary" style="margin-right: 10px;" href="/products/${p.id}/edit">Edit</a>
                	<form action="/products/${p.id}/delete" method="post">
	                	<input type="hidden" name="_method" value="delete" />
	                	<input class="btn btn-danger" type="submit" value="Delete">
					</form>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.0/js/bootstrap.min.js" integrity="sha384-3qaqj0lc6sV/qpzrc1N5DC6i1VRn/HyX4qdPaiEFbn54VjQBEU341pvjz7Dv3n6P" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</body>
</html>