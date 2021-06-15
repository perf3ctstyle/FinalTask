<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
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
        <span class="user-name">
            <fmt:message key="local.users.user"/>
        </span>
    </div>

    <c:forEach var="credential" items="${credentialList}" varStatus="status">
        <div class="user">
            <span class="user-name">
                ${credential.name} ${credential.surname}
            </span>
            <div class="buttons">
                <c:choose>
                    <c:when test="${credential.userId == id}"/>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/controller?command=changeIsUserBlocked&id=${credential.userId}"
                           class="button second-entity-button">
                            <c:choose>
                                <c:when test="${isUserBlockedList[status.index] == false}">
                                    <fmt:message key="local.users.button.block"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="local.users.button.unblock"/>
                                </c:otherwise>
                            </c:choose>
                        </a>
                    <a href="${pageContext.request.contextPath}/controller?command=deleteUser&id=${credential.userId}"
                       class="button second-entity-button"><fmt:message key="local.button.delete"/></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:forEach>

    <div class="pagination-wrapper">
        <div class="pagination">
            <c:if test="${currentPage != 1}">
                <a href="${pageContext.request.contextPath}/controller?command=getAdminMainPage&page=${currentPage-1}">&laquo;</a>
            </c:if>
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <a <c:if test="${currentPage == i}">
                    style="background-color: #426e70; color: white;"
                </c:if>
                        href="${pageContext.request.contextPath}/controller?command=getAdminMainPage&page=${i}">${i}
                </a>
            </c:forEach>
            <c:if test="${currentPage != numberOfPages}">
                <a href="${pageContext.request.contextPath}/controller?command=getAdminMainPage&page=${currentPage+1}">&raquo;</a>
            </c:if>
        </div>
    </div>
</div>
<ftr:footerTag currentYear="2021"/>
</body>
</html>
