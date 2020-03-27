<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Upload</title>
</head>
<body>
	<form:form action="/testupload" method="POST" modelAttribute="image" enctype="multipart/form-data">
		<%-- <p>
			<form:label path="imgName">Title: </form:label>
			<form:input path="imgName" />
			<form:errors style="color:red;" path="imgName" />
		</p> --%>
		<p>
			<label for="imageUrl">Image upload: </label>
			<input type="file" class="form-control" name="imageUrl" multiple/>
			<%-- <form:errors style="color:red;" path="imageUrl" /> --%>
		</p>
		<input type="submit" class="btn btn-primary" value="Upload">
	</form:form>
</body>
</html>