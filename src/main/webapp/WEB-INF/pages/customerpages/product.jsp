<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${product.name}</title>
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
        <div class="col-sm-2">
            <img class="img-thumbnail img-responsive" style="max-height: 280px"
                 src=${product.imageURL}>
        </div>
        <div class="col-sm-4">
            <h2>Name: ${product.name}</h2>
            <h2>Price: ${product.price}</h2>
            <legend>Add product to the cart</legend>
            <c:url var="addProductToCart" value="/cart"/>
            <form:form action="${addProductToCart}" method="POST">
                <input type="hidden" name="productId" value="${product.id}"/>
                <input type="text" name="amount">
                <input type="submit" value="Add">
            </form:form>
        </div>
    </div>
</div>
</div>
</body>
</html>