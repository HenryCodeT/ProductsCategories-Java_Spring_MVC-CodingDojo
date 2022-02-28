<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title> Titulo </title>
	<!-- Bootstrap -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- Estilos Locales -->
	<link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<h1>${category.name}</h1>
<h2>Products</h2>
<ul>
<c:forEach items="${categorysProducts}" var="product">
	<li>${product.name}</li>
</c:forEach>
</ul>

<form action="/addProduct/${ category.id }" method="get">
	<label for="product_id">Add Product:</label>
	<select name="product_id">
	<c:forEach items="${availableProducts}" var="product">
		<option value="${product.id}">${product.name}</option>
	</c:forEach>
	</select>
	<input type="submit" value="Add">
</form>
<a href="/">Home</a>


	<!-- jQuery (No necesario en Bootstrap 5) -->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<!--Bootstrap -->
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<!-- Javascript Local -->
	<script src="/js/app.js"></script>
</body>
</html>