
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Страница преподавателя</title>
    </head>
    <body>
        <h2>${user.firstName} ${user.lastName}</h2>
        <h3>Вы вошли в систему как преподаватель</h3>
        <h4>Выберите операцию:</h4>
        <a href="controller?command=students">Показать список студентов</a> <br>
        <a href="controller?command=operations">Показать список всех операций</a> <br>
        <a href="controller?command=go_to_unblock">Открыть курс</a> <br>
        <a href="controller?command=logout">Выйти из системы</a>
    </body>
</html>
