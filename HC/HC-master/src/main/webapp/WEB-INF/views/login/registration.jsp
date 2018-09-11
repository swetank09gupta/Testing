<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <%@include file="../global.resources/responsiveResource.jsp" %>
</head>

<body class="view">
<div class="" id="wrap">
    <%@include file="../global.resources/top.jsp" %>
    <div id="content">
        <div class="outer">
            <div class="inner bg-light lter">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="box dark">
                            <header>
                                <div class="icons"><i class="fa fa-edit"></i></div>
                                <h5>User Registration</h5>
                            </header>
                            <div id="div-1" class="body">
                                <form:form method="POST" modelAttribute="user" class="form-horizontal">
                                    <form:input type="hidden" path="id" id="id"/>
                                    <div class="form-group">
                                        <label for="firstName" class="control-label col-lg-3">First Name</label>
                                        <span class="col-lg-8">
                                            <form:input type="text" path="firstName" id="firstName" class="form-control"/>
                                            <div class="has-error">
                                                <form:errors path="firstName" class="help-inline"/>
                                            </div>
                                        </span>
                                    </div>

                                    <div class="form-group">
                                        <label for="lastName" class="control-label col-lg-3">Last Name</label>
                                        <span class="col-lg-8">
                                            <form:input type="text" path="lastName" id="lastName" class="form-control"/>
                                            <div class="has-error">
                                                <form:errors path="lastName" class="help-inline"/>
                                            </div>
                                        </span>
                                    </div>

                                    <div class="form-group">
                                        <label for="ssoId" class="control-label col-lg-3">SSO ID</label>
                                        <span class="col-lg-8">
                                        <c:choose>
                                            <c:when test="${edit}">
                                                <form:input type="text" path="ssoId" id="ssoId" class="form-control" disabled="true"/>
                                            </c:when>
                                            <c:otherwise>
                                                <form:input type="text" path="ssoId" id="ssoId" class="form-control"/>
                                                <div class="has-error">
                                                    <form:errors path="ssoId" class="help-inline"/>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                        </span>
                                    </div>

                                    <div class="form-group">
                                        <label for="password" class="control-label col-lg-3">Password</label>
                                        <span class="col-lg-8">
                                            <form:input type="password" path="password" id="password" class="form-control"/>
                                            <div class="has-error">
                                                <form:errors path="password" class="help-inline"/>
                                            </div>
                                        </span>
                                    </div>

                                    <div class="form-group">
                                        <label for="email" class="control-label col-lg-3">Email</label>
                                        <span class="col-lg-8">
                                            <form:input type="text" path="email" id="email" class="form-control"/>
                                            <div class="has-error">
                                                <form:errors path="email" class="help-inline"/>
                                            </div>
                                        </span>
                                    </div>

                                    <div class="form-group">
                                        <label for="userProfiles" class="control-label col-lg-3">Roles</label>
                                        <span class="col-lg-8">
                                            <c:choose>
                                            <c:when test="${edit}">
                                                <sec:authorize access="hasRole('ADMIN')">
                                                    <form:select path="userProfiles"  items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control"/>
                                                </sec:authorize>
                                                <sec:authorize access="hasRole('USER')">
                                                    <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control" disabled="true"/>
                                                </sec:authorize>
                                            </c:when>
                                            <c:otherwise>
                                                <form:select path="userProfiles"  items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control"/>
                                            </c:otherwise>
                                            </c:choose>

                                            <div class="has-error">
                                                <form:errors path="userProfiles" class="help-inline"/>
                                            </div>
                                        </span>
                                    </div>

                                    <div class="form-actions no-margin-bottom">
                                        <c:choose>
                                            <c:when test="${edit}">
                                                <input type="submit" value="Update" class="btn btn-primary">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="submit" value="Register" class="btn btn-primary">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>


</html>