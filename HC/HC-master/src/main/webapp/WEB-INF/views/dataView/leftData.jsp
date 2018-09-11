<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form:form method="POST" modelAttribute="resourceDetails" class="form-horizontal">
    <div class="row form-group">

        <ul id="menu" class="bg-blue dker">
            <li class="nav-header">Menu</li>
            <li class="nav-divider"></li>
            <li class="">
                <form:input type="text" class="form-control" path="name" id="name" placeholder="Name"/>
            </li>
        </ul>
    </div>
</form:form>