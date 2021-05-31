<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftr" uri="footerTag" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/limitNumberInput.js"></script>
</head>
<body>
<div class="sidenav">
    <jsp:include page="fragments/menu-user.jsp"/>
</div>

<div class="header">
    <jsp:include page="fragments/header-logged-in.jsp"/>
</div>

<div id="apply-container" class="main-content-container">
    <form id="apply-form" action="${pageContext.request.contextPath}/controller?command=saveApplication" method="post">
        <div class="apply-title">
            <fmt:message key="local.apply.text"/>
        </div>

        <div class="input-field">
            <fmt:message key="local.apply.faculty"/>
            <select name="facultySelect" required>
                <option value="" selected="selected"><fmt:message key="local.apply.faculty.select"/></option>
                <c:forEach var="faculty" items="${facultyList}">
                    <option>${faculty.name}</option>
                </c:forEach>
            </select>
        </div>

        <c:if test="${isSelectedFacultyValid == false}">
            <div class="invalid">
                Unfortunately, the selected faculty wasn't found. <br />
                Please, try again.
            </div>
        </c:if>

        <div class="input-field">
            <fmt:message key="local.apply.score.certificate"/> <br/>
            <input class="text-input" id="certificate-input" type="number" maxlength="${scoreLength}"
                   oninput="limitNumberInput('certificate-input')"
                   placeholder="<fmt:message key="local.apply.score"/>"
                   name="certificate" required/>
        </div>

        <c:if test="${isCertificateScoreValid == false}">
            <div class="invalid">
                Unfortunately, the certificate score had an inadmissible value. <br />
                Please, try again.
            </div>
        </c:if>

        <div class="input-field">
            <fmt:message key="local.apply.score.userSubject.first"/>
            <input class="text-input" id="first-user-subject-input" type="number" maxlength="${scoreLength}"
                       oninput="limitNumberInput('first-user-subject-input')"
                       placeholder="<fmt:message key="local.apply.score"/>"
                       name="firstSubjectScoreInput" required/>
        </div>

        <div class="input-field">
            <fmt:message key="local.apply.score.userSubject.second"/>
            <input class="text-input" id="second-user-subject-input" type="number" maxlength="${scoreLength}"
                       oninput="limitNumberInput('second-user-subject-input')"
                       placeholder="<fmt:message key="local.apply.score"/>"
                       name="secondSubjectScoreInput" required/>
        </div>

        <div class="input-field">
            <fmt:message key="local.apply.score.userSubject.third"/>
            <input class="text-input" id="third-user-subject-input" type="number" maxlength="${scoreLength}"
                       oninput="limitNumberInput('third-user-subject-input')"
                       placeholder="<fmt:message key="local.apply.score"/>"
                       name="thirdSubjectScoreInput" required/>
        </div>

        <c:if test="${areSubjectsScoresValid == false}">
            <div class="invalid">
                Unfortunately, some of the subjects scores had an inadmissible value. <br />
                Please, try again.
            </div>
        </c:if>

        <div>
            <button class="button apply-faculty-button" type="submit" value="saveApplication"><fmt:message
                    key="local.button.apply"/></button>
        </div>

        <c:if test="${applicationExists == true}">
            <div class="invalid">
                You've already applied to one of the faculties. <br />
                Unfortunately, you cannot send another application.
            </div>
        </c:if>

        <c:if test="${applicationSaved == true}">
            <div class="successful">
                Your application has been successfully saved.
            </div>
        </c:if>
    </form>
</div>
<ftr:footerTag currentYear="2021"/>
</body>
</html>
