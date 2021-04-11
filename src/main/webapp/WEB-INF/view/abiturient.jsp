<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="fragments/internationalization.jsp"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css"/>
</head>
<body>
<div class="header">
    <jsp:include page="fragments/header-logged-in.jsp"/>
</div>

<div class="sidenav">
    <jsp:include page="fragments/menu.jsp"/>
</div>

<div class="main-content-container admin-content-container">
    <div id="main-content-heading" class="abiturient admin-content-heading">
        Ivan Ivanov
    </div>
    <table id="scores">
        <tr>
            <th></th>
            <th><fmt:message key="local.apply.score"/></th>
        </tr>
        <tr>
            <th><fmt:message key="local.abiturient.subject.first"/></th>
            <th>100</th>
        </tr>
        <tr>
            <th><fmt:message key="local.abiturient.subject.second"/></th>
            <th>100</th>
        </tr>
        <tr>
            <th><fmt:message key="local.abiturient.subject.third"/></th>
            <th>100</th>
        </tr>
        <tr>
            <th><fmt:message key="local.abiturient.certificate"/></th>
            <th>100</th>
        </tr>
    </table>
</div>

</body>
</html>
