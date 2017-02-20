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
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <jsp:include page="/WEB-INF/pages/customerpages/templates/topmenu.jsp"/>
</head>
<body class="general-body">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <c:url var="logout" value="/logout"/>
            <form:form action="${logout}" method="POST">
                <input type="submit" class="btn btn-primary pull-right" value="Log out">
            </form:form>
            <jsp:include page="/WEB-INF/pages/customerpages/templates/left-menu.jsp"/>
            <div class="col-md-10">
                <c:forEach var="product" items="${productsToShow.content}">
                    <div class="col-md-3" style="min-height: 450px; border: 1px solid floralwhite">
                        <img class="img-thumbnail img-responsive" style="max-height: 280px"
                             src=${product.imageURL}>
                        <h3>${product.name}</h3>  <h4> Price: ${product.price}</h4>
                        <c:url var="productPage" value="/product/${product.id}"/>
                        <a href="${productPage}" class="btn btn-info" role="button">Details</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${fromSearch}">
            <ul class="pagination pagination-lg">
                <c:forEach begin="1" var="current" end="${numberOfPages}">
                    <c:url var="toPage" value="/search//page/${current}/?${pageContext.request.queryString}"/>
                    <li><a href="${toPage}">${current}</a></li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="pagination pagination-lg">
                <c:forEach begin="1" var="current" end="${numberOfPages}">
                    <c:url var="toPage" value="/page/${current}/?${pageContext.request.queryString}"/>
                    <li><a href="${toPage}">${current}</a></li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>