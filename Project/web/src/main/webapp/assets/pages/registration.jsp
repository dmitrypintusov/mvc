
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         errorPage="/assets/pages/error/error.jsp" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
    <head>
        <title>Регистрация</title>
    </head>
    <body>
        <form name="registration" method="POST" action="controller">
            <input type="hidden" name="command" value="registration" />
            Введите ваши данные:<br>
            Имя:<br>
            <input type="text" name="firstname" value="" size="20" required /><br>
            Фамилия:<br>
            <input type="text" name="lastname" value="" size="20" required /><br>
            Логин:<br>
            <input type="login" name="login" value="" size="20" required /><br>
            Пароль:<br>
            <input type="password" name="password" value="" size="20" required /><br>
            ${operationMessage}
            ${errorUserExists} <br>
            <input type="submit" value="Зарегистрировать" />
            <a href="controller?command=back">Вернуться обратно</a>
        </form>
    </body>
</html>
