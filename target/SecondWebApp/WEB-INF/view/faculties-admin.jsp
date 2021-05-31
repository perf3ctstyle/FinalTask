<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftr" uri="footerTag" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Admission Committee</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css"/>
</head>
<body>
<div class="sidenav">
    <jsp:include page="fragments/menu-admin.jsp"/>
</div>

<div class="header">
    <jsp:include page="fragments/header-logged-in.jsp"/>
</div>

<div class="main-content-container">
    <div id="main-content-heading" class="faculty">
        <span class="faculty-name">
            <fmt:message key="local.faculty.name"/>
        </span>
    </div>

    <c:forEach var="faculty" items="${facultyList}">
        <div class="faculty">
            <a href="${pageContext.request.contextPath}/controller?command=getAdmittedAbiturientsPage&name=${faculty.name}" class="faculty-name">
                    ${faculty.name}
            </a>
            <div class="buttons">
                <a href="${pageContext.request.contextPath}/controller?command=getAdmittedAbiturientsPage&name=${faculty.name}" class="button first-entity-button">
                    <fmt:message key="local.faculty.button.admitted"/>
                </a>
            </div>
        </div>
    </c:forEach>

    <div class="pagination-wrapper">
        <div class="pagination">
            <c:if test="${currentPage != 1}">
                <a href="${pageContext.request.contextPath}/controller?command=getFacultyListPage&page=${currentPage-1}">&laquo;</a>
            </c:if>
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <a <c:if test="${currentPage == i}">
                    style="background-color: #426e70; color: white;"
                </c:if>
                        href="${pageContext.request.contextPath}/controller?command=getFacultyListPage&page=${i}">${i}
                </a>
            </c:forEach>
            <c:if test="${currentPage != numberOfPages}">
                <a href="${pageContext.request.contextPath}/controller?command=getFacultyListPage&page=${currentPage+1}">&raquo;</a>
            </c:if>
        </div>
    </div>
</div>
<ftr:footerTag currentYear="2021"/>
</body>
</html>
