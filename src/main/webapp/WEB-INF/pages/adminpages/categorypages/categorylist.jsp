<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 16.01.2017
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
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
    <jsp:include page="/WEB-INF/pages/adminpages/templates/topmenu.jsp"/>
</head>
<body class="general-body">
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <td colspan="2">Name</td>
                </tr>
                </thead>
                <c:forEach var="category" items="${categories}">
                    <tr>
                        <td><c:out value="${category.name}"/></td>
                        <td>
                            <c:url var="getCategoryInfo" value="/admin/category/${category.id}"/>
                            <a href="${getCategoryInfo}" class="btn btn-info" role="button">Watch products</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <a href="<c:url value="/admin/category/add"/>" class="btn btn-primary pull-right" role="button">Create new
                category</a>
        </div>
    </div>
</div>


</body>
</html>