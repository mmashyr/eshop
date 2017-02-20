<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>
<c:url var="register" value="/registration"/>
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <fieldset>
                <legend>Create new account</legend>
                <form:form modelAttribute="account" method="POST" action="${register}">
                <c:if test="${param.error != null}">
                    <p>Invalid username and password.</p>
                </c:if>
                <c:if test="${param.logout != null}">
                    <p>You have been logged out successfully.</p>
                </c:if>
                <div class="form-group">
                    <label for="username"> Username: </label> <form:errors path="username" cssClass="error"/>
                    <form:input path="username" class="form-control"/>

                    <label for="passwordDto">Password: </label> <form:errors path="passwordDto" cssClass="error"/>
                    <form:password path="passwordDto" class="form-control"/>

                    <label for="firstName">First name: </label> <form:errors path="firstName" cssClass="error"/>
                    <form:input path="firstName" class="form-control"/>

                    <label for="secondName">Second name: </label> <form:errors path="secondName" cssClass="error"/>
                    <form:input path="secondName" class="form-control"/>

                    <label for="address.city">City: </label> <form:errors path="address.city" cssClass="error"/>
                    <form:input path="address.city" class="form-control"/>

                    <label for="address.street">Street: </label> <form:errors path="address.street" cssClass="error"/>
                    <form:input path="address.street" class="form-control"/>

                    <label for="address.houseNumber">House number: </label> <form:errors path="address.houseNumber"
                                                                                         cssClass="error"/>
                    <form:input path="address.houseNumber" class="form-control"/>

                    <label for="address.apartmentNumber">Apartment number:</label> <form:errors
                        path="address.apartmentNumber" cssClass="error"/>
                    <form:input path="address.apartmentNumber" class="form-control"/>

                    <label for="address.phoneNumber">Phone number:</label> <form:errors path="address.phoneNumber"
                                                                                        cssClass="error"/>
                    <form:input path="address.phoneNumber" class="form-control"/>

                </div>
                <input type="submit" class="btn pull-right" value="Register"/>
            </fieldset>
            </form:form>
        </div>
    </div>
</div>
</body>