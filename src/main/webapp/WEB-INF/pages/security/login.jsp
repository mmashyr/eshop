<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>

</head>
<body>
<br>


<c:url var="loginUrl" value="/login"/>
<form:form action="${loginUrl}" method="post">
    <c:if test="${param.error != null}">
        <p>Invalid username and password.</p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>You have been logged out successfully.</p>
    </c:if>
    <label for="username">Login: </label>
    <input type="text" id="username" name="username" placeholder="Enter Login" required> <br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" placeholder="Enter Password" required>

    <input type="submit" value="Log in">

</form:form>


</body>
</html>