<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="fragments/internationalization.jsp"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css"/>
</head>
<body>
<div class="header">
    <jsp:include page="fragments/header-logged-in.jsp"/>
</div>

<div class="sidenav">
    <jsp:include page="fragments/menu.jsp"/>
</div>

<div class="main-content-container">
    <div class="faculty faculty-title">
        Faculty of Information Technologies and Control.
    </div>
    <div class="faculty-content">

        <div class="paragraph faculty-description">
            <div class="title description-title"><fmt:message key="local.faculty.description"/></div>
            <div class="content description-content">The Faculty of Information Technologies and Control is the coeval
                of the University.
                Nowadays the Faculty is a large educational and scientific centre with more than 1,700 students.
                Our academic staff regularly keeps its experience up-to-date in the leading universities of the world.
            </div>
        </div>

        <div class="paragraph faculty-admission">
            <div class="title admission-title"><fmt:message key="local.faculty.plan.admission"/>
                <span class="content admission-content"></span>
            </div>

            <div class="paragraph faculty-apply">
                <div class="title apply-title">
                    <button class="button apply-faculty-button"><fmt:message key="local.faculty.button.apply"/></button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
