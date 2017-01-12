
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         errorPage="/WEB-INF/assets/pages/error/error.jsp" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
    <style type="text/css">
        a:link { text-decoration: none; }
        a:visited { text-decoration: none; }
        a:active { text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>

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
            № курса:<br>
            <input type="text" name="courseid" value="" size="20" required /><br>
            Имя курса:<br>
            <p><select name="coursename" required>
                <option disabled selected>Выберите курс</option>
                <option value="Java">JAVA</option>
                <option value=".Net">.NET</option>
                <option value="HTML">HTML</option>
                <option value="Python">PYTHON</option>
            </select></p>

            ${operationMessage}
            ${errorUserExists} <br>
            <input type="submit" value="Зарегистрировать" />
            <button><a href="controller?command=back">Вернуться обратно</a></button>
        </form>
    </body>
</html>