<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>ИНТЕРНЕТ-БАНК. Список счетов клиента</title>
</head>
<body>
    <jstl:choose>
        <jstl:when test="${billList.size() > 0}">
            <h2>ИНТЕРНЕТ-БАНК</h2>
            <a href="/">Вернуться к списку клиентов</a>
            <h3>Список счетов клиента: ${client.surname} ${client.name} ${client.patronymic}</h3>
            <table cellpadding="5" cellspacing="5">
                <thead>
                <tr>
                    <th>ID</th><th>Остаток средств (руб.)</th>
                </tr>
                </thead>
                <tbody>
                <jstl:forEach var="bill" items="${billList}">
                    <tr>
                        <td>${bill.id}</td>
                        <td>${bill.sum}</td>
                    </tr>
                </jstl:forEach>
                </tbody>
            </table>
        </jstl:when>
        <jstl:otherwise>
            Список счетов пуст :(
        </jstl:otherwise>
    </jstl:choose>
    <h3>Добавление нового счёта:</h3>
    <spring:form method="get"  modelAttribute="billModel" action="/client-bills/${client.id}/add-bill">
        <table cellpadding="5" cellspacing="5">
            <tbody>
            <tr>
                <th align="left">Начальная сумма:</th><th><spring:input path="sum"/></th>
            </tr>
            <tr>
                <spring:hidden path="idClient" value="${client.id}" />
                <th><spring:button>Добавить</spring:button></th><th></th>
            </tr>
            </tbody>
        </table>
    </spring:form>
</body>
