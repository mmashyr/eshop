<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<div class="col-md-8">
    <c:url var="loginUrl" value="/login"/>
    <form:form action="${loginUrl}" method="post">
        <fieldset>
            <div class="form-group">
                <label for="username">Login: </label>
                <input class="form-control" type="text" id="username" name="username" placeholder="Enter Login" required> <br>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input class="form-control" type="password" id="password" name="password" placeholder="Enter Password" required>
            </div>
            <input type="submit" class="btn pull-right" value="login"/>
        </fieldset>
    </form:form>
</div>
</div>
</body>
</html>