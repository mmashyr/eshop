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
<div class="col-sm-4">
    <div class="navbar navbar navbar-fixed-left">
        <c:url var="mainPage" value="/"/>
        <form method="POST" action="${mainPage}">
            <c:forEach var="category" items="${categories}">
            <input type="checkbox" name="categoryIDs" value="${category.id}">${category.name}<Br>
            </c:forEach>
            <input type="submit" value="Submit">
        </form>
    </div>
</div>
</html>