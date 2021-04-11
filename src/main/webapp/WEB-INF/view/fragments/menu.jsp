<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${lang}">

<a id="main" href="${pageContext.servletContext.contextPath}/controller?command=main"><fmt:message key="local.menu.faculties"/></a>
<a id="apply" href="${pageContext.servletContext.contextPath}/controller?command=apply"><fmt:message key="local.button.apply"/></a>
<div id="menu-dropdown" class="dropdown"><button id="menu-dropbtn" class="dropbtn"><fmt:message key="local.button.language"/></button>
    <div class="menu-dropdown-content dropdown-content" style="left:0">
        <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=en">English</a>
        <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=ru">Русский</a>
        <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=by">Беларуская</a>
    </div>
</div>

</html>