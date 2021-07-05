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
    <jsp:include page="fragments/menu-user.jsp"/>
</div>

<div class="header">
    <jsp:include page="fragments/header-logged-in.jsp"/>
</div>

<div id="applications-container" class="main-content-container">
    <div id="main-content-heading" class="user">
        <span class="applicant-name">
            <fmt:message key="local.users.application"/>
        </span>
        <span class="applicant-faculty-name">
            <fmt:message key="local.faculty.name"/>
        </span>
    </div>

    <c:forEach var="credential" items="${credentialList}" varStatus="status">
        <div class="user">
            ${credential.name} ${credential.surname}
            <div class="buttons">
                <span class="applicant-faculty-name">
                        ${facultyNameList[status.index]}
                </span>
            </div>
        </div>
    </c:forEach>

    <div class="pagination-wrapper">
        <div class="pagination">
            <c:if test="${numberOfPages > 1}">
                <c:if test="${currentPage != 1}">
                    <a href="${pageContext.request.contextPath}/controller?command=getAdmittedAbiturientsPage&page=${currentPage-1}">&laquo;</a>
                </c:if>
                <c:forEach begin="1" end="${numberOfPages}" var="i">
                    <a <c:if test="${currentPage == i}">
                        style="background-color: #426e70; color: white;"
                    </c:if>
                            href="${pageContext.request.contextPath}/controller?command=getAdmittedAbiturientsPage&page=${i}">${i}
                    </a>
                </c:forEach>
                <c:if test="${currentPage != numberOfPages}">
                    <a href="${pageContext.request.contextPath}/controller?command=getAdmittedAbiturientsPage&page=${currentPage+1}">&raquo;</a>
                </c:if>
            </c:if>
        </div>
    </div>
</div>
<ftr:footerTag currentYear="2021"/>
</body>
</html>
