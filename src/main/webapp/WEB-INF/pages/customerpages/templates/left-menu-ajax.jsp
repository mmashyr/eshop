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
    <script src="<c:url value="/resources/js/mainpage/searchajax.js"/>"></script>
    <script src="<c:url value="/resources/js/mainpage/checkboxesajax.js"/>"></script>
</head>
<div class="col-md-2">
    <div class="navbar-fixed-left">
        <h3>Search:</h3>
        <c:url var="search" value="/searchajax"/>
        <form:form id="searchform" method="GET" action="${search}">
            <input type="text" name="name" id="searchName"/>
            <input type="submit" value="Search">
        </form:form>
        <br><strong>Or</strong><br>
        <h3>Select a producer:</h3>
        <c:url var="byCategories" value="/bycategories"/>
        <form:form id="categorycheckboxes" method="GET" action="${byCategories}">
            <c:forEach var="category" items="${categories}">
                <input type="checkbox" name="producer" class="producer" value="${category.name}">${category.name}<Br>
            </c:forEach>
            <input type="submit" value="Submit">
        </form:form>
        <a class="btn btn-info showAllButton" role="button">Reset</a>
    </div>
</div>
</html>