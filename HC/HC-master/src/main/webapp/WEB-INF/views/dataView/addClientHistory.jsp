<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Client History</title>
    <%@include file="../global.resources/responsiveResource.jsp" %>
</head>

<body class="view">

<div class="row">
    <div class="col-lg-12">
        <div class="box dark">
            <header>
                <div class="icons"><i class="fa fa-edit"></i></div>
                <h5>Add Client History</h5>
            </header>
            <div id="div-1" class="body">
                <form:form method="POST" modelAttribute="historyDetails" class="form-horizontal">

                    <form:input type="hidden" path="id" id="id"/>

                    <form:input type="hidden" path="addedBy" id="addedBy" value="${userSSOId}"/>

                    <form:input type="hidden" path="resourceID" id="resourceID"/>

                    <form:input type="hidden" path="resourceHistoryID" id="resourceHistoryID"/>

                    <div class="form-group">
                        <label for="client" class="control-label col-lg-3">Client</label>

                        <span class="col-lg-3">
                            <form:select path="client" items="${clients}" itemValue="id" itemLabel="clientName"
                                         class="form-control input-sm"/>
                        </span>

                        <label for="clientStatus" class="control-label col-lg-3">Candidate Status</label>

                        <span class="col-lg-3">
                            <form:select path="clientStatus" items="${clientStatuses}" itemValue="id" itemLabel="clientStatusName"
                                         class="form-control input-sm"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="positionName" class="control-label col-lg-3">Position</label>

                        <span class="col-lg-3">
                            <form:select path="positionName" items="${positions}" itemValue="id"
                                         itemLabel="positionName"
                                         class="form-control input-sm"/>
                        </span>

                        <label for="remarks" class="control-label col-lg-3">Recruiter Comments</label>

                        <span class="col-lg-3">
                            <form:input type="textbox" path="remarks" id="remarks"
                                        placeholder="Recruiter Comments" class="form-control"/>
                        </span>
                    </div>

                    <div class="form-actions no-margin-bottom">
                        <input type="submit" value="Add History" class="btn btn-primary">
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>

</body>

<script>
    $(document).ready(function(){
        $.colorbox({inline:true, href:".row", onClosed: function () {
                window.location.href = "<c:url value='/dataList' />";
            }
        });
    });

</script>
</html>
