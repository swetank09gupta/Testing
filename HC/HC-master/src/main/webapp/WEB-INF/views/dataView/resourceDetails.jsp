<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Resource Details</title>

</head>

<body>
<div class="col-lg-12 resourceDetail" style="width:768px;">
    <div class="box">
        <div id="collapse4" class="body">
            <table class="table table-striped responsive-table">
                <tbody>
                <tr>
                    <td><b>Name</b></td>
                    <td>${resourceDetail.name}</td>
                    <td><b>Contact No.</b></td>
                    <td>${resourceDetail.contactNumber}</td>
                </tr>
                <tr>
                    <td><b>Email</b></td>
                    <td>${resourceDetail.emailId}</td>
                    <td><b>Institute</b></td>
                    <td>${resourceDetail.institute.instituteName}</td>
                </tr>
                <tr>
                    <td><b>Stream</b></td>
                    <td>${resourceDetail.stream.streamName}</td>
                    <td><b>Program</b></td>
                    <td>${resourceDetail.program.programName}</td>
                </tr>
                <tr>
                    <td><b>CGPA</b></td>
                    <td>${resourceDetail.CGPA}</td>
                    <td><b>Air Rank</b></td>
                    <td>${resourceDetail.airRank}</td>
                </tr>
                <tr>
                    <td><b>College Rank</b></td>
                    <td>${resourceDetail.otherRank}</td>
                    <td><b>Area Expertise</b></td>
                    <td>${resourceDetail.areaOfExpertise}</td>
                </tr>
                <tr>
                    <td><b>Skills</b></td>
                    <td>${resourceDetail.skills}</td>
                    <td><b>Designation</b></td>
                    <td>${resourceDetail.designation}</td>
                </tr>
                <tr>
                    <td><b>Company</b></td>
                    <td>${resourceDetail.company}</td>
                    <td><b>Experience(Years)</b></td>
                    <td>${resourceDetail.experience}</td>
                </tr>
                <tr>
                    <td><b>Fixed CTC</b></td>
                    <td>${resourceDetail.fixedCTC}</td>
                    <td><b>Variable CTC</b></td>
                    <td>${resourceDetail.variableCTC}</td>
                </tr>
                <tr>
                    <td><b>Expected CTC</b></td>
                    <td>${resourceDetail.expectedCTC}</td>
                    <td><b>Notice Period</b></td>
                    <td>${resourceDetail.noticePeriod}</td>
                </tr>
                <tr>
                    <td><b>Current Location</b></td>
                    <td>${resourceDetail.currentLocation.locationName}</td>
                    <td><b>Preferred Location</b></td>
                    <td>${resourceDetail.preferredLocation.locationName}</td>
                </tr>
                <tr>
                    <td><b>Linkedin Profile</b></td>
                    <td colspan="3">${resourceDetail.linkedinProfile}</td>
                </tr>
                <tr>
                    <td><b>Facebook Profile</b></td>
                    <td colspan="3">${resourceDetail.facebookProfile}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script>
    /*$(document).ready(function(){
        parent.jQuery.colorbox({inline:true, href:".resourceDetail"});
    });*/

    $("#cboxClose").click(function () {
        $.colorbox.close();
        return false;
    });
</script>
</html>
