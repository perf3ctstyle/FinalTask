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
<a id="admitted" href="${pageContext.request.contextPath}/controller?command=getAdmittedAbiturientsPage"><fmt:message
        key="local.menu.admitted"/></a>
</body>
</html>