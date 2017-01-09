
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Операции</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table border="1">
            <tr bgcolor="#f0ffff">
                <td align="center"><strong>Дата</strong></td>
                <td><strong>Описание</strong></td>
                <td><strong>Номер пользователя</strong></td>
                <td><strong>Номер курса</strong></td>
            </tr>
            <c:forEach var="operation" items="${operationsList}">
                <tr>
                    <td><c:out value="${operation.date}" /></td>
                    <td><c:out value="${operation.discription}" /></td>
                    <td><c:out value="${operation.userId}" /></td>
                    <td><c:out value="${operation.courseId}" /></td>
                </tr>
            </c:forEach>
        </table>
        <a href="controller?command=back_teacher">Вернуться обратно</a>
        <a href="controller?command=logout">Выйти из системы</a>
    </body>
</html>
