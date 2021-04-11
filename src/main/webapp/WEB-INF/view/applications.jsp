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
    <a id="applicationsPage" href="${pageContext.request.contextPath}/controller?command=applyPage"><fmt:message key="local.menu.applications"/></a>
</div>
<div id="applications-container" class="main-content-container">
    <div id="main-content-heading" class="abiturient">
  <span class="abiturient-name">
  <fmt:message key="local.applications.abiturient.name"/>
  </span>
        <span class="abiturient-score">
  <fmt:message key="local.applications.abiturient.score"/>
  </span>
    </div>

    <div class="abiturient">
        <a href="#" class="abiturient-name">
            Ivan Ivanov
        </a>
        <div class="score-about">
            <button class="button details-button"><fmt:message key="local.applications.button.details"/></button>
            <span class="abiturient-score">
  100
  </span>
        </div>
    </div>

    <div class="abiturient">
        <a href="#" class="abiturient-name">
            Vasya Pupkin
        </a>
        <div class="score-about">
            <button class="button details-button">Details</button>
            <span class="abiturient-score">
  100
  </span>
        </div>
    </div>

    <div class="abiturient">
        <a href="#" class="abiturient-name">
            Vasya Pupkin
        </a>
        <div class="score-about">
            <button class="button details-button">Details</button>
            <span class="abiturient-score">
  100
  </span>
        </div>
    </div>

    <div class="abiturient">
        <a href="#" class="abiturient-name">
            Vasya Pupkin
        </a>
        <div class="score-about">
            <button class="button details-button">Details</button>
            <span class="abiturient-score">
  100
  </span>
        </div>
    </div>

    <div class="abiturient">
        <a href="#" class="abiturient-name">
            Vasya Pupkin
        </a>
        <div class="score-about">
            <button class="button details-button">Details</button>
            <span class="abiturient-score">
  100
  </span>
        </div>
    </div>

    <div class="abiturient">
        <a href="#" class="abiturient-name">
            Vasya Pupkin
        </a>
        <div class="score-about">
            <button class="button details-button">Details</button>
            <span class="abiturient-score">
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
