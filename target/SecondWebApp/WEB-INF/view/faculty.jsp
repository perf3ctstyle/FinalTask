<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css"/>
</head>
<body>
<div class="sidenav">
    <jsp:include page="fragments/menu.jsp"/>
</div>

<div class="header">
    <jsp:include page="fragments/header-logged-in.jsp"/>
</div>

<div class="main-content-container">
    <div class="faculty faculty-title">
        ${faculty.name}
    </div>
    <div class="faculty-content">

        <div class="paragraph faculty-description">
            <div class="title description-title"><fmt:message key="local.faculty.description"/></div>
            <div class="content description-content">${faculty.description}</div>
        </div>

        <div class="paragraph faculty-admission">
            <div class="title admission-title"><fmt:message key="local.faculty.plan.admission"/>
                <span class="content admission-content">${faculty.admissionPlan}</span>
            </div>

            <div class="paragraph faculty-apply">
                <div class="title apply-title">
                    <a href="${pageContext.request.contextPath}/controller?command=apply" class="button apply-faculty-button"><fmt:message key="local.faculty.button.apply"/></a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
