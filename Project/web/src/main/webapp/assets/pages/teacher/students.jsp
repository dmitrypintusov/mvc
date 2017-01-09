
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Студенты</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table border="1">
            <tr bgcolor="#fff8dc">
                    <td align="center"><stong>Фамилия</stong></td>
                    <td><stong>Имя</stong></td>
            </tr>
            <c:forEach var="student" items="${userList}">
                <tr>
                    <td><c:out value="${student.lastName}" /></td>
                    <td><c:out value="${student.firstName}" /></td>
                </tr>
            </c:forEach>
        </table>
        <a href="controller?command=back_teacher">Вернуться обратно</a>
        <a href="controller?command=logout">Выйти из системы</a>
    </body>
</html>
