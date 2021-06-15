<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<h1 class="header-text">
    <fmt:message key="local.title"/>
</h1>
<div class="header-buttons">
    <div class="dropdown">
        <img src="${pageContext.request.contextPath}/images/change_language_icon.png" class="dropbtn dropbtn-logged-in" alt="Change Language"
        width="50" height="50">
        <div class="dropdown-content">
            <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=en">English</a>
            <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=ru">Русский</a>
            <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=by">Беларуская</a>
        </div>
    </div>
    <a href="${pageContext.request.contextPath}/controller?command=logout" class="button logout-button">
        <fmt:message key="local.header.button.logout"/>
    </a>
</div>
</body>
