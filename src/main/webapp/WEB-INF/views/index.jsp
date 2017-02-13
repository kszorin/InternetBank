<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>ИНТЕРНЕТ-БАНК. Список клиентов</title>
</head>
<body>
    <jstl:choose>
        <jstl:when test="${clientList.size() > 0}">
            <h2>ИНТЕРНЕТ-БАНК</h2>
            <h3>Список всех клиентов:</h3>
            <table cellpadding="5" cellspacing="5">
                <thead>
                <tr>
                    <th>ID</th><th>Фамилия</th><th>Имя</th><th>Отчество</th><th>Адрес</th>
                </tr>
                </thead>
                <tbody>
                <jstl:forEach var="client" items="${clientList}">
                    <tr>
                        <td>${client.id}</td>
                        <td><a href="/client-bills/${client.id}">${client.surname}</a></td>
                        <td>${client.name}</td>
                        <td>${client.patronymic}</td>
                        <td>${client.address}</td>
                    </tr>
                </jstl:forEach>
                </tbody>
            </table>
        </jstl:when>
        <jstl:otherwise>
            Список клиентов пуст :(
        </jstl:otherwise>
    </jstl:choose>
    <h3>Добавление нового клиента:</h3>
    <jstl:if test="${resultString eq 'error'}">
        Ошибка! Все поля должны быть заполнены!
    </jstl:if>
    <jstl:if test="${resultString eq 'success'}">
        Клиент успешно добавлен!
    </jstl:if>
    <spring:form method="post"  modelAttribute="clientModel" action="/add-client">
        <table cellpadding="5" cellspacing="5">
            <tbody>
                <tr>
                    <th align="left">Фамилия:</th><th><spring:input path="surname"/></th>
                </tr>
                <tr>
                    <th align="left">Имя:</th><th><spring:input path="name"/></th>
                </tr>
                <tr>
                    <th align="left">Отчество:</th><th><spring:input path="patronymic"/></th>
                </tr>
                <tr>
                    <th align="left">Адрес:</th><th><spring:input path="address"/></th>
                </tr>
                <tr>
                    <th><spring:button>Добавить</spring:button></th><th></th>
                </tr>
            </tbody>
        </table>
    </spring:form>
</body>