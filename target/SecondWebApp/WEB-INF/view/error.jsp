<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Admission Committee</title>
</head>
<body>
<div class="header">
    <jsp:include page="fragments/header-logged-out.jsp"/>
</div>
<h2 style="color: red;"><fmt:message key="local.error"/></h2>

<div>
    <c:out value="${errorMessage}" />
</div>

</body>
</html>