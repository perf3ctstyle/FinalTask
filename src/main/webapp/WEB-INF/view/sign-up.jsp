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
    <h1 class="header-text">
        <fmt:message key="local.title"/>
    </h1>
    <div class="header-buttons">
        <div class="dropdown">
            <img src="${pageContext.request.contextPath}/images/change_language_icon.png" class="dropbtn dropbtn-logged-out" alt="Change Language"
                 width="50" height="50">
            <div class="dropdown-content">
                <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=en">English</a>
                <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=ru">Русский</a>
                <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=by">Беларуская</a>
            </div>
        </div>
    </div>
</div>

<div class="login-container">
    <form action="${pageContext.request.contextPath}/controller?command=signUp" method="post">
        <div class="login-text">
            <h3><fmt:message key="local.signup"/></h3>
        </div>
        <div class="signup-text">
            <fmt:message key="local.signup.data.login"/>
        </div>
        <div>
            <fmt:message key="local.username"/>
        </div>
        <div class="input-field">
            <input class="text-input" type="text" placeholder="<fmt:message key="local.username"/>"
                   name="username" required/>
        </div>
        <c:if test="${loginExists == true}">
            <div class="invalid">
                <fmt:message key="local.signup.error.login"/>
            </div>
        </c:if>
        <div>
            <fmt:message key="local.password"/>
        </div>
        <div class="input-field">
            <input class="text-input" type="password" placeholder="<fmt:message key="local.password"/>"
                   name="password" required/>
        </div>
        <div>
            <fmt:message key="local.password.repeat"/>
        </div>
        <div class="input-field">
            <input class="text-input" type="password" placeholder="<fmt:message key="local.password.repeat"/>"
                   name="repeatedPassword" required/>
        </div>
        <c:if test="${passwordsDoNotMatch == true}">
            <div class="invalid">
                <fmt:message key="local.signup.error.password.match"/>
            </div>
        </c:if>
        <br />
        <div class="signup-text">
            <fmt:message key="local.signup.data.credential"/>
        </div>
        <div>
            <fmt:message key="local.name"/>
        </div>
        <div class="input-field">
            <input class="text-input" type="text" placeholder="<fmt:message key="local.name"/>"
                   name="name" required/>
        </div>
        <div>
            <fmt:message key="local.surname"/>
        </div>
        <div class="input-field">
            <input class="text-input" type="text" placeholder="<fmt:message key="local.surname"/>"
                   name="surname" required/>
        </div>
        <c:if test="${invalidCredentials == true}">
            <div class="invalid">
                <fmt:message key="local.signup.error.credential"/>
            </div>
        </c:if>
        <div>
            <button class="button login-button" type="submit" value="signup">
                <fmt:message key="local.button.signup"/>
            </button>
        </div>
    </form>
</div>
</body>
</html>
