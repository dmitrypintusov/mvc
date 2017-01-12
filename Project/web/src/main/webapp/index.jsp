<%@ page contentType="text/html; charset=UTF-8" 
		 pageEncoding="UTF-8" errorPage="/WEB-INF/assets/pages/error/error.jsp"%>
		 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
	<style type="text/css">
		a:link { text-decoration: none; }
		a:visited { text-decoration: none; }
		a:active { text-decoration: none; }
		a:hover { text-decoration: underline; }
	</style>

	<head>
		<title>Авторизация</title>
	</head>
	<body>
		<form name ="loginForm" method="POST" action="controller">
			<input type="hidden" name="command" value="login" />
			Введите ваш логин и пароль: <br>
			Логин:<br>
			<input type="text" name="login" value="" size="20" required /><br>
			Пароль:<br>
			<input type="password" name="password" value="" size="20" required /><br>
			${errorLoginOrPassword} <br>
			<input type="submit" value="Войти">
			<button><a href="controller?command=go_to_registration">Регистрация</a></button>
		</form>
	</body>
</html>