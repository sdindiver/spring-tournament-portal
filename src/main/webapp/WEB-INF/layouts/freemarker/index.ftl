<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tournament Portal</title>
</head>
<body>
	<div id="successful_login" class="fix-middle">
		<div class="container text-center">
			<h1>Welcome back to the internet!</h1>
			<p>
				You've successfully managed to log into a nonexistant account in
				order to test a login dialog box.<br> If you like it, you are
				welcomed to use it wherever you want, no strings attached.<br>
				<br>
				<a href="#" class="link dialog-reset">Rerun the whole thing.</a>
			</p>
		</div>
	</div>
	<div id="dialog" class="dialog dialog-effect-in">
		<div class="dialog-front">
			<div class="dialog-content">
				<form id="login_form" class="dialog-form" action="signIn" method="POST">
					<fieldset>
						<legend>Log in</legend>
						<div class="form-group">
							<label for="userName" class="control-label">userName:</label>
							<input type="text" id="userName" class="form-control"
								name="userName" autofocus />
						</div>
						<div class="form-group">
							<label for="password" class="control-label">Password:</label>
							<input type="password" id="password" class="form-control"
								name="password" />
						</div>
						<div class="text-center pad-top-20">
							<p>
								Have you forgotten your<br>
								<a href="#" class="link"><strong>userName</strong></a> or <a
									href="#" class="link"><strong>password</strong></a>?
							</p>
						</div>
						<div class="pad-top-20 pad-btm-20">
							<input type="submit" class="btn btn-default btn-block btn-lg"
								value="Continue">
						</div>
						<div class="text-center">
							<p>
								Do you wish to register<br> for <a href="register"
									class="link user-actions"><strong>a new account</strong></a>?
							</p>
						</div>
					</fieldset>
				</form>
			</div>
		</div>

	</div>
	<link rel="stylesheet" type="text/css"
		href="resources/css/style.css">
	<script type="text/javascript"
		src="resources/js/jquery.js"></script>
		<link rel="stylesheet prefetch" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<script type="text/javascript"
		src="resources/js/bottom.js"></script>
</body>
</html>