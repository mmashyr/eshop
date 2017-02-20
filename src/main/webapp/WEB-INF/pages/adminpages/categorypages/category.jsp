<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage ${category.name}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <jsp:include page="/WEB-INF/pages/adminpages/templates/topmenu.jsp"/>
</head>
<body class="general-body">
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <h2>Category: ${category.name} </h2>
            <table class="table">
                <thead>
                <tr>
                    <td>Product</td>
                    <td>Price</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${category.products}">
                    <tr>
                        <c:url var="infoAboutProduct" value="admin/product/${product.id}/"/>
                        <td><a href="${infoAboutProduct}">${product.name}</a></td>
                        <td>${product.price}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:url value="/admin/category/" var="categories"/>
            <a href="${categories}">Get back to the list of categories</a>
        </div>
    </div>
</div>
</body>
</html>