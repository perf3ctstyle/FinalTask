<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Admission Committee</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css"/>
</head>
<body>
<div class="header">
    <jsp:include page="WEB-INF/view/fragments/header-logged-out.jsp"/>
</div>
<div class="login-container">
    <h2 style="color: red;"><fmt:message key="local.error"/></h2>
    <div>
        ${errorMessage}
    </div>
    <div>
        <c:choose>
            <c:when test="${id == null}">
                <a href="${pageContext.request.contextPath}"
                   class="button return-button"><fmt:message key="local.error.return.login"/></a>
            </c:when>
            <c:when test="${role == 'ADMIN'}">
                <a href="${pageContext.request.contextPath}/controller?command=getAdminMainPage"
                   class="button return-button"><fmt:message key="local.error.return.main"/></a>
            </c:when>
            <c:when test="${role == 'ABITURIENT'}">
                <a href="${pageContext.request.contextPath}/controller?command=getFacultyListPage"
                   class="button return-button"><fmt:message key="local.error.return.main"/></a>
            </c:when>
        </c:choose>
    </div>
</div>

</body>
</html>