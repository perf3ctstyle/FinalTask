<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${lang}">
<head>
    <title><fmt:message key="local.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css"/>
</head>
<body>
<div class="header">
    <jsp:include page="WEB-INF/view/fragments/header-logged-out.jsp"/>
</div>

<div class="login-container">
    <form action="${pageContext.request.contextPath}/controller?command=login" method="post">
        <div class="login-text">
            <h3><fmt:message key="local.index.text"/></h3>
        </div>
        <div class="input-field">
            <input class="text-input" type="text" placeholder="<fmt:message key="local.index.username"/>"
                   name="username"/>
        </div>
        <div class="input-field">
            <input class="text-input" type="password" placeholder="<fmt:message key="local.index.password"/>"
                   name="password"/>
        </div>
        <div>
            <button class="button login-button" type="submit" value="login"><fmt:message
                    key="local.index.button.login"/></button>
        </div>
        <div class="dropdown">
            <button class="button dropbtn"><fmt:message key="local.button.language"/></button>
            <div class="dropdown-content">
                <a href="${pageContext.servletContext.contextPath}?lang=en">English</a>
                <a href="${pageContext.servletContext.contextPath}?lang=ru">Русский</a>
                <a href="${pageContext.servletContext.contextPath}?lang=by">Беларуская</a>
            </div>
        </div>
        <c:if test="${invalidLogin == true}">
            <div class="invalid">
                <fmt:message key="local.index.invalid.login"/>
            </div>
        </c:if>
        <c:if test="${isBlocked == true}">
            <div class="invalid">
                <fmt:message key="local.index.invalid.login.blocked"/>
            </div>
        </c:if>
    </form>
</div>
</body>
</html>
