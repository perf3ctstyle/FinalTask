<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftr" uri="footerTag" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/limitNumberInput.js"></script>
</head>
<body>

<div class="sidenav">
    <jsp:include page="fragments/menu-admin.jsp"/>
</div>

<div class="header">
    <jsp:include page="fragments/header-logged-in.jsp"/>
</div>

<div class="main-content-container admin-content-container">
    <div id="main-content-heading" class="user admin-content-heading">
        ${name} ${surname}
    </div>
    <table class="abiturient-info">
        <tr>
            <th><fmt:message key="local.faculty.name"/></th>
            <th>${facultyName}</th>
        </tr>
    </table>
    <table class="abiturient-info">
        <tr>
            <th></th>
            <th><fmt:message key="local.apply.score"/></th>
        </tr>
        <c:forEach var="subjectScore" items="${subjectsScoreList}">
            <tr>
                <th><fmt:message key="local.abiturient.subject"/></th>
                <th>${subjectScore}</th>
            </tr>
        </c:forEach>
        <tr>
            <th><fmt:message key="local.abiturient.certificate"/></th>
            <th>${certificateScore}</th>
        </tr>
    </table>
    <a id="application-change-button" class="button change-info-button" href="${pageContext.request.contextPath}/controller?command=getApplicationData">
        <fmt:message key="local.button.application.change"/>
    </a>
</div>
<ftr:footerTag currentYear="2021"/>
</body>
</html>
