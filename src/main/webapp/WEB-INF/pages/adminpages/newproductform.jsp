<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:url var="addProduct" value="/product/add"/>
<div class="container">
    <fieldset>
        <legend>Add new Product</legend>
        <form:form modelAttribute="product" method="POST" action="${addProduct}">
        <div class="form-group">
            <label for="name">Name: </label>
            <form:input path="name" class="form-control"/>
            <form:errors path="name" cssClass="error"/><br>
        </div>
        <div class="form-group">
            <label for="price">Price: </label>
            <form:input path="price" class="form-control"/>
            <form:errors path="price" cssClass="error"/><br>
        </div>
        <input type="submit" class="btn btn-default" value="Submit"/>
    </fieldset>
    </form:form>
</div>
</body>
</html>