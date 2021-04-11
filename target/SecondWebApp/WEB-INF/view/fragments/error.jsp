<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Admission Committee</title>
</head>
<body>
<h2 style="color: red;">An error occurred!</h2>
<c:if test="${param.errorMessage != null}">
    ${param.errorMessage}
</c:if>
<%= request.getAttribute("errorMessage") %>
</body>
</html>
