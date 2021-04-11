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
</div>
<div id="applications-container" class="main-content-container">

</div>

</body>
</html>
