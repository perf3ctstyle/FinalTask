<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftr" uri="footerTag" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css"/>
</head>
<body>

<div class="sidenav">
    <jsp:include page="fragments/menu-admin.jsp"/>
</div>

<div class="header">
    <jsp:include page="fragments/header-logged-in.jsp"/>
</div>

<div id="applications-container" class="main-content-container">
    <div id="main-content-heading" class="user">
        <span class="applicant-name">
            <fmt:message key="local.users.application"/>
        </span>
    </div>

    <c:forEach var="credential" items="${credentialList}" varStatus="status">
        <div class="user">
            <a href="${pageContext.request.contextPath}/controller?command=getAbiturientInfoPage&id=${credential.userId}&name=${credential.name}&surname=${credential.surname}" class="applicant-name">
                <fmt:message key="local.users.application"/> ${credential.name} ${credential.surname}
            </a>
            <div class="buttons">
                <a href="${pageContext.request.contextPath}/controller?command=getAbiturientInfoPage&id=${credential.userId}&name=${credential.name}&surname=${credential.surname}" class="button second-entity-button">
                    <fmt:message key="local.users.button.details"/>
                </a>
                <c:if test="${isApplicationReviewedList[status.index] == false}">
                    <a href="${pageContext.request.contextPath}/controller?command=approveApplication&id=${applicationIdList[status.index]}&approve=false"
                       class="button second-entity-button">
                        <fmt:message key="local.button.decline"/>
                    </a>
                    <a href="${pageContext.request.contextPath}/controller?command=approveApplication&id=${applicationIdList[status.index]}&approve=true"
                       class="button second-entity-button">
                        <fmt:message key="local.button.approve"/>
                    </a>
                </c:if>
            </div>
        </div>
    </c:forEach>

    <div class="pagination-wrapper">
        <div class="pagination">
            <c:if test="${currentPage != 1}">
                <a href="${pageContext.request.contextPath}/controller?command=getApplicationsPage&page=${currentPage-1}">&laquo;</a>
            </c:if>
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <a <c:if test="${currentPage == i}">
                    style="background-color: #426e70; color: white;"
                </c:if>
                        href="${pageContext.request.contextPath}/controller?command=getApplicationsPage&page=${i}">${i}
                </a>
            </c:forEach>
            <c:if test="${currentPage != numberOfPages}">
                <a href="${pageContext.request.contextPath}/controller?command=getApplicationsPage&page=${currentPage+1}">&raquo;</a>
            </c:if>
        </div>
    </div>
</div>
<ftr:footerTag currentYear="2021"/>
</body>
</html>
