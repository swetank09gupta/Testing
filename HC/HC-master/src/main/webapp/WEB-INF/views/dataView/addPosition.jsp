<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Position</title>
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
                                <h5>Add Position</h5>
                            </header>
                            <div id="div-1" class="body">
                                <form:form method="POST" modelAttribute="position" class="form-horizontal">
                                    <form:input type="hidden" path="id" id="id"/>
                                    <form:input type="hidden" path="addedBy" id="addedBy" value="${userSSOId}"/>
                                    <div class="form-group">
                                        <label for="positionName" class="control-label col-lg-3">Position</label>
                                        <span class="col-lg-8">
                                            <form:input type="text" path="positionName" id="positionName"
                                                        class="form-control" data-validation="required"/>
                                            <div class="has-error">
                                                <form:errors path="positionName" class="help-inline"/>
                                            </div>
                                        </span>
                                    </div>

                                    <div class="form-actions no-margin-bottom">
                                        <c:choose>
                                            <c:when test="${edit}">
                                                <input type="submit" value="Update" class="btn btn-primary">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="submit" value="Add" class="btn btn-primary">
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
<script>
    $.validate();
</script>
