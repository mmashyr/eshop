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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <jsp:include page="/WEB-INF/pages/adminpages/templates/topmenu.jsp"/>
</head>
<body>
<c:url var="linkToProduct" value="/product/"/>
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <c:forEach var="entry" items="${accountAndBookings}">
                <h1>${entry.key.firstName}</h1>
                <c:forEach var="booking" items="${entry.value}">
                    <h3>Booking date: <mytags:localDateTime date="${booking.dateTime}" pattern="yyyy.MM.dd HH:mm"/></h3>
                    <h4>Status: ${booking.orderStatus} </h4>
                    <table class="table table-bordered">
                        <thead class="thead">
                        <tr>
                            <th>Product name</th>
                            <th>Amount</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="productsInBooking" items="${booking.productsInBooking}">
                            <tr>
                                <td>
                                    <a href="<c:url value="/product/${productsInBooking.key.id}"/>">${productsInBooking.key.name}
                                </td>
                                <td>${productsInBooking.value}</td>
                                <td>${productsInBooking.key.price}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan=" 2"><strong>Total</strong>
                            </td>
                            <td align="right"><strong><em>${booking.totalPrice}</em></strong></td>
                        </tr>
                        </tbody>
                    </table>
                    <c:url var="takeBooking" value="/courier/booking/add/${booking.id}"/>
                    <c:if test="${booking.orderStatus == 'NEW'}">
                        <form:form action="${takeBooking}" method="POST">
                            <input type="submit" class="btn btn-primary pull-right" value="Take booking"><br>
                        </form:form>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>