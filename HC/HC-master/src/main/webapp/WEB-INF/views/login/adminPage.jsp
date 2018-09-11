<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin Links</title>
    <%@include file="../global.resources/responsiveResource.jsp" %>
</head>

<body>
<div class="generic-container">
    <%@include file="../global.resources/top.jsp" %>
    <div class="panel panel-default">
        <sec:authorize access="hasRole('ADMIN')">
            <div class="well">
                <a href="<c:url value='/newuser' />">Add New User</a>
            </div>
            <div class="well">
                <a href="<c:url value='/list' />">Users List</a>
            </div>
        </sec:authorize>
        <div class="well">
            <a href="<c:url value='/edit-user-${userSSOId}' />">Edit Details</a>
        </div>
    </div>
</div>
</body>
</html>