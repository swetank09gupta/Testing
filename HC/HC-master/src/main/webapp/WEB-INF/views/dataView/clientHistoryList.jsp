<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Clients History</title>
</head>

<body>
    <div class="col-lg-12" style="max-height: 800px; min-height: 500px;">
        <div class="box">
            <header>
                <div class="icons"><i class="fa fa-table"></i></div>
                <h5>Client History</h5>
            </header>
            <div id="collapse4" class="body">
                <table id="dataTableClientHistory"
                       class="table table-bordered table-condensed table-hover table-striped dataTable no-footer">
                    <thead>
                    <tr>
                        <th>Client</th>
                        <th>Position</th>
                        <th>Status</th>
                        <th>Recruiter</th>
                        <th>Added Date</th>
                        <th>Recruiter Comments</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${clientHistoryList}" var="clientHistory">
                        <tr>
                            <td nowrap="nowrap"><a class="resourceHistoryDetails"
                                                   href="${pageContext.request.contextPath}/resourceHistoryDetails-${clientHistory.resourceHistoryID}"
                                                   title="Resource History Details for ${resourceDetails.name}">${clientHistory.client.clientName}</a></td>
                            <td nowrap="nowrap">${clientHistory.positionName.positionName}</td>
                            <td nowrap="nowrap">${clientHistory.clientStatus.clientStatusName}</td>
                            <td nowrap="nowrap">${clientHistory.addedBy}</td>
                            <td nowrap="nowrap">${clientHistory.addedDate}</td>
                            <td>${clientHistory.remarks}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
</div>
</body>
</html>

<script>
    $("#cboxClose").click(function () {
        $.colorbox.close();
        return false;
    });
    $(function () {
        $(".resourceHistoryDetails").colorbox({
            onClosed: function () {
                jQuery.colorbox.close();
            }
        });
        $('#dataTableClientHistory').dataTable();
    });
</script>