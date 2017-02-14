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
                <c:url var="removeImage" value="/resources/img/remove_product.png"/>
                <c:forEach var="salePosition" items="${cart.salePositions}">
                    <tr>
                        <td>${salePosition.key.name}</td>
                        <td>${salePosition.key.price}</td>
                        <td>${salePosition.value}</td>
                        <td>
                            <c:url var="remove" value="/cart/remove/${salePosition.key.id}/"/>
                            <form:form action="${remove}" method="DELETE">
                                <input type="image" src="${removeImage}">
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                <td>Total: ${cart.totalPrice}</td>
                </tbody>
            </table>
            <c:url var="save" value="/cart/save"/>
            <form:form action="${save}" method="POST">
                <input type="submit" class="btn btn-primary pull-right" value="Check out">
            </form:form>
        </div>
    </div>
</div>
</body>
</html>