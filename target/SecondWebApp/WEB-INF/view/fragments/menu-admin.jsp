<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${lang}">
<body>
<a id="main" href="${pageContext.servletContext.contextPath}/controller?command=getAdminMainPage"><fmt:message
        key="local.menu.users"/></a>
<a id="applications" href="${pageContext.request.contextPath}/controller?command=getApplicationsPage"><fmt:message
        key="local.menu.applications"/></a>
<a id="registers" href="${pageContext.request.contextPath}/controller?command=getRegistersPage"><fmt:message
        key="local.menu.register"/></a>
<div id="menu-dropdown" class="dropdown">
    <button id="menu-dropbtn" class="dropbtn"><fmt:message key="local.button.language"/></button>
    <div class="menu-dropdown-content dropdown-content" style="left:0">
        <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=en">English</a>
        <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=ru">Русский</a>
        <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=by">Беларуская</a>
    </div>
</div>
</body>
</html>