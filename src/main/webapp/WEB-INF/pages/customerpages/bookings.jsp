<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <table class="table">
                <thead>
                <tr>
                    <td>Name</td>
                    <td>Price</td>
                    <td>Amount</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="booking" items="${bookings}">
                    <tr>
                        <td>${salePosition.key.name}</td>
                        <td>${salePosition.key.price}</td>
                        <td>${salePosition.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>