<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<div class="col-md-2">
    <div class="navbar-fixed-left">
        <h3>Search:</h3>
        <c:url var="search" value="/search/page/1/"/>
        <form:form method="GET" action="${search}">
            <input type="text" name="name"/>
            <input type="submit" value="Submit">
        </form:form>
        <br><strong>Or</strong><br>
        <h3>Select a producer:</h3>
        <c:url var="mainPage" value="/page/1"/>
        <form:form method="GET" action="${mainPage}">
            <c:forEach var="category" items="${categories}">
                <input type="checkbox" name="producer" value="${category.name}">${category.name}<Br>
            </c:forEach>
            <input type="submit" value="Submit">
        </form:form>
    </div>
</div>
</html>