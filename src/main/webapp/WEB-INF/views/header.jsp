<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href = "${pageContext.request.contextPath}/css/style.css">
    <title>FABORIS</title>
</head>
<body>
    <header class="main-header">
        <div>
            <h1 class="logo"><a href="${pageContext.request.contextPath}">Faboris</a></h1>
        </div>
            <nav class="menu">
                <span><a href="${pageContext.request.contextPath}/cart">Корзина</a></span>
                <span class="profile"><a href="${pageContext.request.contextPath}/auth">Профиль</a></span>
            </nav>
    </header>
</body>
</html>