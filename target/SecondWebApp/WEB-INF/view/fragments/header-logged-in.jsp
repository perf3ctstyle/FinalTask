<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<h1 class="header-text">
    <fmt:message key="local.title"/>
</h1>
    <a href="${pageContext.request.contextPath}/controller?command=logout" class="button logout-button">
        <fmt:message key="local.header.button.logout"/>
    </a>
</body>
