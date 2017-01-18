<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage ${product.name}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <jsp:include page="/WEB-INF/pages/adminpages/templates/topmenu.jsp"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <h2>Name: ${product.name}</h2>
            <h2>Price: ${product.price}</h2>
            <table class="table">
                <thead>
                <tr>
                    <td>Category Name</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="category" items="${product.categories}">
                    <tr>
                        <c:url var="infoAboutCategoty" value="admin/category/${category.id}/"/>
                        <td><a href="${infoAboutCategory}">${category.name}</a></td>
                        <td>
                            <c:url var="removeCategory"
                                   value="/admin/product/${product.id}/category/${category.id}"/>
                            <form:form action="${removeCategory}" method="DELETE">
                                <input type="submit" class="btn pull-right" value="Remove Category">
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <fieldset>
                <legend>Add category</legend>
                <c:url var="addCategory" value="/admin/product/${product.id}/category/add/"/>
                <form:form method="POST" action="${addCategory}" modelAttribute="category">
                    <table class="table">
                        <tr>
                            <td>Category :</td>
                            <td><form:select class="form-control" path="id">
                                <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                            </form:select>
                                <button class="btn btn-reg">Add category</button>
                            </td>
                        </tr>
                        <tr>
                        </tr>
                    </table>
                </form:form>
            </fieldset>

            <c:url value="/admin/product/" var="products"/>
            <a href="${products}">Get back to the list of products</a>
        </div>
    </div>
</div>
</body>
</html>