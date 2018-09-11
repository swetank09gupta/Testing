<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="POST" modelAttribute="resourceDetails" class="form-horizontal">
    <%--<div id="flip"><a href="#">Show / Hide Filters</a></div>--%>
    <div id="panel" class="row form-group">
        <div class="form-group">
            <span class="col-lg-3">
                            <form:input type="text" path="name" id="name" placeholder="Name"
                                        class="form-control"/>
                        </span>
            <span class="col-lg-3">
                            <form:input type="text" path="contactNumber" id="contactNumber"
                                        placeholder="Contact No." class="form-control"/>
                        </span>
            <span class="col-lg-3">
                            <form:input type="text" path="emailId" id="emailId" placeholder="Email"
                                        class="form-control"/>
                        </span>
            <span class="col-lg-3">
                            <form:select path="institute" class="form-control input-sm">
                                <form:option value="" label="--- Select Institute ---"/>
                                <form:options items="${institutes}" itemValue="id" itemLabel="instituteName"/>
                            </form:select>
                        </span>
        </div>

        <div class="form-group">
            <span class="col-lg-3">
                            <form:select path="stream" class="form-control input-sm">
                                <form:option value="" label="--- Select Stream ---"/>
                                <form:options items="${streams}" itemValue="id" itemLabel="streamName"/>
                            </form:select>
                        </span>
            <span class="col-lg-3">
                            <form:select path="program" class="form-control input-sm">
                                <form:option value="" label="--- Select Program ---"/>
                                <form:options items="${programs}" itemValue="id" itemLabel="programName"/>
                            </form:select>
                        </span>
            <span class="col-lg-3">
                            <form:select path="passingYear" class="form-control input-sm">
                                <form:option value="" label="--- Select Passing Year ---"/>
                                <form:options items="${passingYears}" itemValue="id" itemLabel="passingYear"/>
                            </form:select>
                        </span>
            <span class="col-lg-3">
                            <form:input type="text" path="noticePeriod" id="noticePeriod" placeholder="Notice Period"
                                        class="form-control"/>
                        </span>
        </div>


        <div class="form-group">

            <span class="col-lg-3">
                            <form:input type="text" path="experience" id="experience" placeholder="Experience"
                                        class="form-control"/>
                        </span>

            <span class="col-lg-3">
                            <form:input type="text" path="fixedCTC" id="fixedCTC" placeholder="Fixed CTC"
                                        class="form-control"/>
                        </span>
            <span class="col-lg-3">
                            <form:input type="text" path="variableCTC" id="variableCTC" placeholder="Variable CTC"
                                        class="form-control"/>
                        </span>
            <span class="col-lg-3">
                            <form:input type="text" path="expectedCTC" id="expectedCTC" placeholder="Expected CTC"
                                        class="form-control"/>
                        </span>

        </div>

        <div>
            <input type="submit" value="Search" class="floatRight btn btn-metis-6 btn-sm"/>
        </div>

    </div>
</form:form>

<%--<script>
    $(document).ready(function () {
        $("#flip").click(function () {
            $("#panel").slideToggle("slow");
        });
    });
</script>--%>

<style>
    #panel{
        padding: 5px;
        text-align: center;

    }

    #panel {
        padding-top: 5px;
        padding-left: 50px;
        padding-right: 50px;
    }
</style>

