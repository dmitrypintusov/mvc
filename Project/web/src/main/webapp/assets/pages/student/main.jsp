
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Страница студента</title>
    </head>
    <body>
        <h2>${user.firstName} ${user.lastName}</h2>
        <h3>Вы вошли в систему</h3>
        <h4>Выберите операцию:</h4>
        <a href="controller?command=courses">Активные курсы</a> <br>
        <a href="controller?command=attend_course">Записаться на курс</a> <br>
        <a href="controller?command=teachers">Посмотреть список преподавателей</a> <br>
        <a href="controller?command=logout">Выйти из системы</a>
    </body>
</html>
