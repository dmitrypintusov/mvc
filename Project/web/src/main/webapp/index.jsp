<%@ page contentType="text/html; charset=UTF-8" 
		 pageEncoding="UTF-8" errorPage="/assets/pages/error/error.jsp"%>
		 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
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
			<input type="password" name="password" value="" size="" required /><br>
			${errorLoginOrPassword} <br>
			<input type="submit" value="Войти">
			<a href="controller?command=go_to_registration">Регистрация</a>
		</form>
	</body>
</html>