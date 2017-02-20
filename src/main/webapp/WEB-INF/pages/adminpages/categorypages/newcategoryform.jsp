<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <jsp:include page="/WEB-INF/pages/adminpages/templates/topmenu.jsp"/>
</head>
<body class="general-body">
<c:url var="addCategory" value="/admin/category/add"/>
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <fieldset>
                <legend>Create new category</legend>
                <form:form modelAttribute="category" method="POST" action="${addCategory}">
                <div class="form-group">
                    <label for="name">Name: </label>
                    <form:input path="name" class="form-control"/>
                    <form:errors path="name" cssClass="error"/><br>
                </div>
                <input type="submit" class="btn pull-right" value="Submit"/>
            </fieldset>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>