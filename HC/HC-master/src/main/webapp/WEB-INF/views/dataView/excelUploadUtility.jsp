<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Excel Upload Utility</title>
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
                                <h5>Excel Upload Utility</h5>
                            </header>
                            <div id="div-1" class="body">
                                <form:form method="POST" modelAttribute="filePath" class="form-horizontal">
                                    <div class="form-group">
                                        <label for="filePath" class="control-label col-lg-3">Excel File Path</label>
                                        <span class="col-lg-8">
                                            <input type="text" name="filePath" id="filePath" class="form-control input-sm"/>
                                        </span>
                                    </div>
                                    <div class="form-group" style="text-decoration-color: red">
                                        ${errorMessage}
                                    </div>
                                    <div class="form-actions no-margin-bottom">
                                        <input type="submit" value="Upload" class="btn btn-primary">
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