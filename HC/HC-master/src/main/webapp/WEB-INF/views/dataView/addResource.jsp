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

<div class="row">
    <div class="col-lg-12">
        <div class="box dark">
            <header>
                <div class="icons"><i class="fa fa-edit"></i></div>
                <h5>Add Resource</h5>
            </header>
            <div id="div-1" class="body">
                <form:form method="POST" modelAttribute="resourceDetails" class="form-horizontal">

                    <form:input type="hidden" path="id" id="id"/>

                    <form:input type="hidden" path="addedBy" id="addedBy" value="${userSSOId}"/>

                    <div class="form-group">
                        <label for="name" class="control-label col-lg-3">Name</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="name" id="name" placeholder="Name"
                                        class="form-control" data-validation="required"/>
                        </span>

                        <label for="contactNumber" class="control-label col-lg-3">Contact No.</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="contactNumber" id="contactNumber"
                                        placeholder="Contact No." class="form-control"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="emailId" class="control-label col-lg-3">Email</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="emailId" id="emailId" placeholder="Email"
                                        class="form-control" data-validation="email" data-validation-optional="false"/>
                        </span>

                        <label for="institute" class="control-label col-lg-3">Institutes</label>

                        <span class="col-lg-3">
                            <form:select path="institute" items="${institutes}" itemValue="id" itemLabel="instituteName"
                                         class="form-control input-sm"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="stream" class="control-label col-lg-3">Stream</label>

                        <span class="col-lg-3">
                            <form:select path="stream" items="${streams}" itemValue="id" itemLabel="streamName"
                                         class="form-control input-sm"/>
                        </span>

                        <label for="program" class="control-label col-lg-3">Program</label>

                        <span class="col-lg-3">
                            <form:select path="program" items="${programs}" itemValue="id" itemLabel="programName"
                                         class="form-control input-sm"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="passingYear" class="control-label col-lg-3">Passing Year</label>

                        <span class="col-lg-3">
                            <form:select path="passingYear" items="${passingYears}" itemValue="id"
                                         itemLabel="passingYear"
                                         class="form-control input-sm"/>
                        </span>
                        <label for="CGPA" class="control-label col-lg-3">CGPA</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="CGPA" id="CGPA" placeholder="CGPA"
                                        class="form-control" data-validation="number" data-validation-allowing="float" data-validation-optional="true"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="airRank" class="control-label col-lg-3">AIR Rank</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="airRank" id="airRank" placeholder="AIR Rank"
                                        class="form-control" data-validation="number" data-validation-allowing="float" data-validation-optional="true"/>
                        </span>
                        <label for="otherRank" class="control-label col-lg-3">College Rank</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="otherRank" id="otherRank" placeholder="College Rank"
                                        class="form-control" data-validation="number" data-validation-allowing="float" data-validation-optional="true"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="areaOfExpertise" class="control-label col-lg-3">Area Of Expertise</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="areaOfExpertise" id="areaOfExpertise"
                                        placeholder="Area Of Expertise"
                                        class="form-control"/>
                        </span>
                        <label for="skills" class="control-label col-lg-3">Skills</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="skills" id="skills" placeholder="Skills"
                                        class="form-control"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="designation" class="control-label col-lg-3">Designation</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="designation" id="designation" placeholder="Designation"
                                        class="form-control"/>
                        </span>

                        <label for="company" class="control-label col-lg-3">Company</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="company" id="company" placeholder="Company"
                                        class="form-control"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="experience" class="control-label col-lg-3">Experience(Years.Months)</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="experience" id="experience" placeholder="Experience"
                                        class="form-control" data-validation="number" data-validation-allowing="float" data-validation-optional="true"/>
                        </span>
                        <label for="fixedCTC" class="control-label col-lg-3">Fixed CTC(Lacs)</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="fixedCTC" id="fixedCTC" placeholder="Fixed CTC"
                                        class="form-control" data-validation="number" data-validation-allowing="float" data-validation-optional="true"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="variableCTC" class="control-label col-lg-3">Variable CTC(Lacs)</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="variableCTC" id="variableCTC" placeholder="Variable CTC"
                                        class="form-control" data-validation="number" data-validation-allowing="float" data-validation-optional="true"/>
                        </span>
                        <label for="expectedCTC" class="control-label col-lg-3">Expected CTC(Lacs)</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="expectedCTC" id="expectedCTC" placeholder="Expected CTC"
                                        class="form-control" data-validation="number" data-validation-allowing="float" data-validation-optional="true"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="noticePeriod" class="control-label col-lg-3">Notice Period(Months)</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="noticePeriod" id="noticePeriod" placeholder="Notice Period"
                                        class="form-control" data-validation="number" data-validation-allowing="float" data-validation-optional="true"/>
                        </span>
                        <label for="currentLocation" class="control-label col-lg-3">Current Location</label>

                        <span class="col-lg-3">
                            <form:select path="currentLocation" items="${locations}" itemValue="id"
                                         itemLabel="locationName"
                                         class="form-control input-sm"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="preferredLocation" class="control-label col-lg-3">Preferred Location</label>

                        <span class="col-lg-3">
                            <form:select path="preferredLocation" items="${locations}" itemValue="id"
                                         itemLabel="locationName"
                                         class="form-control input-sm"/>
                        </span>
                        <label for="linkedinProfile" class="control-label col-lg-3">Linkedin Profile</label>

                        <span class="col-lg-3">
                            <form:input type="text" path="linkedinProfile" id="linkedinProfile"
                                        placeholder="Linkedin Profile"
                                        class="form-control"/>
                        </span>
                    </div>

                    <div class="form-group">
                        <label for="facebookProfile" class="control-label col-lg-3">Facebook Profile</label>

                        <span class="col-lg-9">
                            <form:input type="text" path="facebookProfile" id="facebookProfile"
                                        placeholder="Facebook Profile"
                                        class="form-control"/>
                        </span>
                    </div>

                    <div class="form-actions no-margin-bottom">
                        <c:choose>
                            <c:when test="${fromAddClientHistory}">
                                <input type="submit" value="Update and Send to Client" class="btn btn-primary">
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${edit}">
                                        <input type="submit" value="Update Resource" class="btn btn-primary">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="submit" value="Add Resource" class="btn btn-primary">
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>

</body>

</html>
<script>
    $.validate();
</script>
