<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="POST" modelAttribute="filterClientHistory" class="form-horizontal">
    <div id="flip"><a href="#">Show / Hide Filters</a></div>
    <div id="panel" class="row form-group">
        <div class="form-group">
            <span class="col-lg-4">
                            <form:select path="client" class="form-control input-sm">
                                <form:option value="" label="--- Select Client ---"/>
                                <form:options items="${clients}" itemValue="id" itemLabel="clientName"/>
                            </form:select>
                        </span>
            <span class="col-lg-4">
                            <form:select path="clientStatus" class="form-control input-sm">
                                <form:option value="" label="--- Select Candidate Status ---"/>
                                <form:options items="${clientStatuses}" itemValue="id" itemLabel="clientStatusName"/>
                            </form:select>
                        </span>
            <span class="col-lg-4">
                            <form:select path="positionName" class="form-control input-sm">
                                <form:option value="" label="--- Select Position ---"/>
                                <form:options items="${positions}" itemValue="id" itemLabel="positionName"/>
                            </form:select>
                        </span>
        </div>

        <div>
            <input type="submit" value="Search" class="floatRight btn btn-metis-6 btn-sm"/>
        </div>

    </div>
</form:form>

<script>
    $(document).ready(function () {
        $("#flip").click(function () {
            $("#panel").slideToggle("slow");
        });
    });
</script>

<style>
    #panel, #flip {
        padding: 5px;
        text-align: center;

    }

    #panel {
        padding-top: 5px;
        padding-left: 50px;
        padding-right: 50px;
        display: none;
    }
</style>

