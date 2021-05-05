<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Admission Committee</title>
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
    <div id="main-content-heading" class="faculty">
  <span class="faculty-name">
  <fmt:message key="local.faculty.name"/>
  </span>
        <span class="faculty-plan">
  <fmt:message key="local.faculty.plan.admission"/>
  </span>
    </div>

    <c:forEach var="faculty" items="${facultyList}">
        <div class="faculty">
            <a href="${pageContext.request.contextPath}/controller?command=faculty&id=${faculty.id}" class="faculty-name">
                ${faculty.name}
            </a>
            <div class="plan-about-apply">
                <a href="${pageContext.request.contextPath}/controller?command=faculty&id=${faculty.id}" class="button about-button"><fmt:message key="local.faculty.button.about"/></a>
                <a href="${pageContext.request.contextPath}/controller?command=apply" class="button apply-button"><fmt:message key="local.button.apply"/></a>
                <span class="faculty-plan">${faculty.admissionPlan}</span>
            </div>
        </div>
    </c:forEach>

    <div class="pagination-wrapper">
        <div class="pagination">
            <c:if test="${currentPage != 1}">
                <a href="${pageContext.request.contextPath}/controller?command=main&page=${currentPage-1}">&laquo;</a>
            </c:if>
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <a href="${pageContext.request.contextPath}/controller?command=main&page=${i}">${i}</a>
            </c:forEach>
            <c:if test="${currentPage != numberOfPages}">
                <a href="${pageContext.request.contextPath}/controller?command=main&page=${currentPage+1}">&raquo;</a>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
