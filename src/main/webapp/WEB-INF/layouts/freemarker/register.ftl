<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tournament Portal</title>
</head>
<body>
	<#if (RequestParameters['success']??)!false>
	<div class="fix-middle">
		<div class="container text-center">
			<h1>Welcome to the internet!</h1>
			<p>
				You've successfully managed to register for a nonexistant account in
				order to test a registration dialog box.<br> If you like it,
				you are welcomed to use it wherever you want, no strings attached.<br>
				<br>
				<a href="${springMacroRequestContext.contextPath}" class="link dialog-reset">Back To Home</a>
			</p>
		</div>
	</div>
	<#else>
	<#if (RequestParameters['error']== '1')!false>
		<div class="error">
		<div class="container text-center">
			<p>Please correct Sign up Details</p>
		</div>
	</div>
	<#elseif (RequestParameters['error'] == '2')!false> 
	<div class="error">
		<div class="container text-center">
			<p>UserName already exists</p>
		</div>
	</#if>
	<#assign signupUrl = 'register'/>
	<div id="dialog" class="dialog dialog-effect-in">
		<div class="dialog-front">
			<div class="dialog-content">
				<form id="register_form" class="dialog-form" action=${signupUrl} method="POST">
					<fieldset>
						<legend>Register</legend>
						<div class="form-group">
							<label for="userName" class="control-label">userName:</label>
							<input type="text" id="userName" class="form-control"
								name="userName" />
						</div>
						<div class="form-group">
							<label for="password" class="control-label">Password:</label>
							<input type="password" id="password" class="form-control"
								name="password" />
						</div>
						<div class="form-group">
							<label for="confirmPassword" class="control-label">Confirm
								password:</label> <input type="password" id="confirmPassword"
								class="form-control" name="confirmPassword" />
						</div>
						<div class="form-group pad-top-20 form-group-checkbox">
							<div class="checkbox">
								<label> <input type="checkbox" id="terms"
									name="terms"> I have read and I agree with the
									Terms and Conditions
								</label>
							</div>
						</div>
						<div class="pad-btm-20">
							<input type="submit" class="btn btn-default btn-block btn-lg"
								value="Continue" />
						</div>
						<div class="text-center">
							<p>
								Return to <a href="signIn" class="link user-actions"><strong>log
										in page</strong></a>?
							</p>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	</#if>

	<link rel="stylesheet" type="text/css"
		href="resources/css/style.css">
	<script type="text/javascript"
		src="resources/js/jquery.js"></script>
		<link rel="stylesheet prefetch" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<script type="text/javascript"
		src="resources/js/bottom.js"></script>
</body>
</html>