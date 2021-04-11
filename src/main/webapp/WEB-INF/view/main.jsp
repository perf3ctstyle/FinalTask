<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="fragments/internationalization.jsp"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Admission Committee</title>
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
    <div id="main-content-heading" class="faculty">
  <span class="faculty-name">
  <fmt:message key="local.faculty.name"/>
  </span>
        <span class="faculty-plan">
  <fmt:message key="local.faculty.plan.admission"/>
  </span>
    </div>

    <div class="faculty">
        <a href="${pageContext.request.contextPath}/controller?command=faculty" class="faculty-name">
            Faculty of Information Technologies And Control
        </a>
        <div class="plan-about-apply">
            <button class="button about-button"><fmt:message key="local.faculty.button.about"/></button>
            <button class="button apply-button"><fmt:message key="local.button.apply"/></button>
            <span class="faculty-plan">
  100
  </span>
        </div>
    </div>

    <div class="faculty">
        <a href="#" class="faculty-name">
            Faculty of Information Technologies And Control
        </a>
        <div class="plan-about-apply">
            <button class="button about-button">About</button>
            <button class="button apply-button">Apply</button>
            <span class="faculty-plan">
  100
  </span>
        </div>
    </div>

    <div class="faculty">
        <a href="#" class="faculty-name">
            Faculty of Information Technologies And Control
        </a>
        <div class="plan-about-apply">
            <button class="button about-button">About</button>
            <button class="button apply-button">Apply</button>
            <span class="faculty-plan">
  100
  </span>
        </div>
    </div>

    <div class="faculty">
        <a href="#" class="faculty-name">
            Faculty of Information Technologies And Control
        </a>
        <div class="plan-about-apply">
            <button class="button about-button">About</button>
            <button class="button apply-button">Apply</button>
            <span class="faculty-plan">
  100
  </span>
        </div>
    </div>

    <div class="faculty">
        <a href="#" class="faculty-name">
            Faculty of Information Technologies And Control
        </a>
        <div class="plan-about-apply">
            <button class="button about-button">About</button>
            <button class="button apply-button">Apply</button>
            <span class="faculty-plan">
  100
  </span>
        </div>
    </div>

    <div class="faculty">
        <a href="#" class="faculty-name">
            Faculty of Information Technologies And Control
        </a>
        <div class="plan-about-apply">
            <button class="button about-button">About</button>
            <button class="button apply-button">Apply</button>
            <span class="faculty-plan">
  100
  </span>
        </div>
    </div>

    <div class="pagination-wrapper">
        <div class="pagination">
            <a href="#">&laquo;</a>
            <a href="#" class="active">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">&raquo;</a>
        </div>
    </div>
</div>

</body>
</html>
