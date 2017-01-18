<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bootstrap Case</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<c:url var="product" value="/admin/product/"/>
<c:url var="category" value="/admin/category/"/>
<c:url var="order" value="/admin/order/"/>
<c:url var="employee" value="/admin/employee/"/>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">eShop admin panel</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="${product}">Products</a></li>
            <li><a href="${category}">Categories</a></li>
            <li><a href="${order}">Orders</a></li>
            <li><a href="${employee}">Employees</a></li>
        </ul>
    </div>
</nav>
</body>
</html>