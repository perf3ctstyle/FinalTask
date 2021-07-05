<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${lang}">
<head>
    <title><fmt:message key="local.title"/></title>
</head>
<body>
<h1 class="header-text">
    <fmt:message key="local.title"/>
</h1>
<div class="header-buttons">
    <div class="dropdown">
        <img src="${pageContext.request.contextPath}/images/change_language_icon.png" class="dropbtn dropbtn-logged-out"
             alt="Change Language"
             width="50" height="50">
        <div class="dropdown-content">
            <a href="${pageContext.servletContext.contextPath}?lang=en">English</a>
            <a href="${pageContext.servletContext.contextPath}?lang=ru">Русский</a>
            <a href="${pageContext.servletContext.contextPath}?lang=by">Беларуская</a>
        </div>
    </div>
</div>
</body>
</html>