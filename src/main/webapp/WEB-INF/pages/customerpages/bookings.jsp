<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <jsp:include page="/WEB-INF/pages/customerpages/templates/topmenu.jsp"/>
</head>
<body class="general-body">
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <c:forEach var="booking" items="${bookings}">
                <div style="border: 1px solid black">
                    <h3>Booking date: <mytags:localDateTime date="${booking.dateTime}" pattern="yyyy.MM.dd HH:mm"/></h3>
                    <h3>Booking status: ${booking.orderStatus}</h3>
                    <h3>Delivery type: ${booking.deliveryType}</h3>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Product name</th>
                            <th>Amount</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="productsInBooking" items="${booking.productsInBooking}">
                            <tr>
                                <td>${productsInBooking.key.name}</td>
                                <td>${productsInBooking.value}</td>
                                <td>${productsInBooking.key.price}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="2">Total</td>
                            <td align="right">${booking.totalPrice}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>