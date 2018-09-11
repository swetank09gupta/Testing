<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login page</title>
		<%@include file="../global.resources/responsiveResource.jsp" %>
	</head>


	<body class="login">

	<div class="form-signin">
		<div class="text-center">
			<img src="<c:url value='/static/images/logo.png'/>" alt="HuntingCube Logo">
		</div>
		<hr>
		<div class="tab-content">
			<div id="login" class="tab-pane active">
				<c:url var="loginUrl" value="/login" />
				<form action="${loginUrl}" method="post">
					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							<p>Invalid username and password.</p>
						</div>
					</c:if>
					<c:if test="${param.logout != null}">
						<div class="alert alert-success">
							<p>You have been logged out successfully.</p>
						</div>
					</c:if>
					<p class="text-muted text-center">
						Enter your username and password
					</p>
					<input type="text" placeholder="Username" id="username" name="ssoId" class="form-control top">
					<input type="password" placeholder="Password" id="password" name="password" class="form-control bottom">
					<%--<div class="checkbox">
						<label>
							<input type="checkbox"> Remember Me
						</label>
					</div>--%>
					<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
					<input type="submit"
						   class="btn btn-lg btn-primary btn-block" value="Sign in">
				</form>
			</div>
			<div id="forgot" class="tab-pane">
				<form action="index.html">
					<p class="text-muted text-center">Enter your valid e-mail</p>
					<input type="email" placeholder="mail@domain.com" class="form-control">
					<br>
					<button class="btn btn-lg btn-danger btn-block" type="submit">Recover Password</button>
				</form>
			</div>
			<div id="signup" class="tab-pane">
				<form action="index.html">
					<input type="text" placeholder="username" class="form-control top">
					<input type="email" placeholder="mail@domain.com" class="form-control middle">
					<input type="password" placeholder="password" class="form-control middle">
					<input type="password" placeholder="re-password" class="form-control bottom">
					<button class="btn btn-lg btn-success btn-block" type="submit">Register</button>
				</form>
			</div>
		</div>
		<hr>
		<div class="text-center">
			<ul class="list-inline">
				<%--<li><a class="text-muted" href="#login" data-toggle="tab">Login</a></li>
				<li><a class="text-muted" href="#forgot" data-toggle="tab">Forgot Password</a></li>
				<li><a class="text-muted" href="#signup" data-toggle="tab">Signup</a></li>--%>
			</ul>
		</div>
	</div>

	<script type="text/javascript">
        (function($) {
            $(document).ready(function() {
                $('.list-inline li > a').click(function() {
                    var activeForm = $(this).attr('href') + ' > form';
                    //console.log(activeForm);
                    $(activeForm).addClass('animated fadeIn');
                    //set timer to 1 seconds, after that, unload the animate animation
                    setTimeout(function() {
                        $(activeForm).removeClass('animated fadeIn');
                    }, 1000);
                });
            });
        })(jQuery);
	</script>
	</body>



	<%--<body>
		<div id="mainWrapper">
			<div class="login-container">
				<div class="login-card">
					<div class="login-form">
						<c:url var="loginUrl" value="/login" />
						<form action="${loginUrl}" method="post" class="form-horizontal">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Invalid username and password.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>You have been logged out successfully.</p>
								</div>
							</c:if>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
								<input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
							</div>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
								<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
							</div>
							<div class="input-group input-sm">
                              <div class="checkbox">
                                <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>  
                              </div>
                            </div>
							<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
								
							<div class="form-actions">
								<input type="submit"
									class="btn btn-block btn-primary btn-default" value="Log in">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</body>--%>
</html>