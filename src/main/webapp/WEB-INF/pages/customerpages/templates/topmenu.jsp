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

<c:url var="bookings" value="/booking/"/>
<c:url var="cart" value="/cart/"/>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Cell phones store</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/eshop">Home</a></li>
            <li><a href="${bookings}">Orders</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <c:url var="cartImage" value="/resources/img/shopping-cart.png"/>
            <li><a href="${cart}"><img src="${cartImage}"></a></li>
        </ul>
    </div>
</nav>
</body>
</html>