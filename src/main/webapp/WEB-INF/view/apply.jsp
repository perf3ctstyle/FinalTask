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

<div id="apply-container" class="main-content-container">
    <form id="apply-form" action="${pageContext.request.contextPath}/controller" method="post">
        <div class="apply-title">
            <fmt:message key="local.apply.text"/>
        </div>
        <div>
            <input type="hidden" name="command" value="apply"/>
        </div>
        <div class="input-field">
            <fmt:message key="local.apply.faculty"/>
            <select name="faculty" id="faculty">
                <option value="" selected="selected"><fmt:message key="local.apply.faculty.select"/></option>
                <c:forEach var="faculty" items="${facultyList}">
                    <option value="faculty" selected="selected">${faculty.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="input-field">
            <fmt:message key="local.apply.score.certificate"/> <br/>
            <input class="text-input" type="text" placeholder="<fmt:message key="local.apply.score"/>"
                   name="certificate"/>
        </div>
        <div class="input-field">
            <fmt:message key="local.apply.score.subject.first"/>
            <select name="faculty" id="first-subject" class="subject-select">
                <option value="" selected="selected"><fmt:message key="local.apply.score.subject.select"/></option>
                <c:forEach var="subject" items="${subjectList}">
                    <option value="first">${subject.name}</option>
                </c:forEach>
                <input class="text-input" type="text" placeholder="<fmt:message key="local.apply.score"/>"
                       name="first-subject"/>
            </select>
        </div>
        <div class="input-field">
            <fmt:message key="local.apply.score.subject.second"/>
            <select name="faculty" id="second-subject" class="subject-select">
                <option value="" selected="selected"><fmt:message key="local.apply.score.subject.select"/></option>
                <c:forEach var="subject" items="${subjectList}">
                    <option value="second">${subject.name}</option>
                </c:forEach>
                <input class="text-input" type="text" placeholder="<fmt:message key="local.apply.score"/>"
                       name="second-subject"/>
            </select>
        </div>
        <div class="input-field">
            <fmt:message key="local.apply.score.subject.third"/>
            <select name="faculty" id="third-subject" class="subject-select">
                <option value="" selected="selected"><fmt:message key="local.apply.score.subject.select"/></option>
                <c:forEach var="subject" items="${subjectList}">
                    <option value="third">${subject.name}</option>
                </c:forEach>
                <input class="text-input" type="text" placeholder="<fmt:message key="local.apply.score"/>"
                       name="third-subject"/>
            </select>
        </div>
        <div>
            <button class="button apply-faculty-button" type="submit" value="apply"><fmt:message
                    key="local.button.apply"/></button>
        </div>
    </form>
</div>

</body>
</html>
