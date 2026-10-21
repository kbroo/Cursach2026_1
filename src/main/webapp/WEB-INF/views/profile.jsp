<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href = "${pageContext.request.contextPath}/css/style.css">
    <title>Document</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <main class="profile-container">
        <div>
            <span>
                Ваш ID: <c:out value="${user.userId}" />
            </span>
        </div>
        <div>
            <span>
                Ваш логин: <c:out value="${user.userName}" />
            </span>
        </div>
        <div>
            <span>
                Ваша почта: <c:out value="${user.userEmail}" />
            </span>
        </div>
    </main>
</body>
</html>