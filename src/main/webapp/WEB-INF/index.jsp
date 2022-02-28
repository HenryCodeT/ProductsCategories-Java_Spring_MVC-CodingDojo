<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Titulo</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- Estilos Locales -->
<link rel="stylesheet" href="/css/style.css" />
</head>
<body>
	<h1>Welcome!</h1>

<div class="outerBox">
	<h2>New Product</h2>
	<div class="innerBox">
		<form:form action="/products/new" method="POST" modelAttribute="product">
			<form:label path="name">Name:
			<form:errors path="name"/>
			<form:input path="name"/>
			</form:label>
			
			<form:label path="description">Description:
			<form:errors path="description"/>
			<form:input path="description"/>
			</form:label>
			
			<form:label path="price">Price:
			<form:errors path="price"/>
			<form:input path="price"/>
			</form:label>
			<input type="submit" value="Create">
		</form:form>
	</div>
	
	<div class="innerBox">
		<table>
			<tr>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
			</tr>
			<c:forEach items="${products}" var="product">
			<tr>
				<td><a href="products/${product.id}">${product.name}</a></td>
				<td>${product.description}</td>
				<td>$ ${product.price}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>

<div id="Category" class="outerBox">
	<h2>New Category</h2>
	<div class="innerBox">
		<form:form action="/category/new" method="POST" modelAttribute="category">
			<form:label path="name">Name:
			<form:errors path="name"/>
			<form:input path="name"/>
			</form:label>
			<input type="submit" value="Create">
		</form:form>
	</div>
	
	<div class="innerBox">
		<h2>Categories:</h2>
		<table>
		<tr>
			<th>Category Name:</th>
		</tr>
		<c:forEach items="${categories}" var="category">
			<tr>
				<td><a href="category/${category.id}">${category.name}</a></td>
			</tr>
		</c:forEach>
		</table>
	</div>
</div>

	<!-- jQuery (No necesario en Bootstrap 5) -->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<!--Bootstrap -->
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<!-- Javascript Local -->
	<script src="/js/app.js"></script>
</body>
</html>