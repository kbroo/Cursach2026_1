<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
        <div class="header-logo-and-search">
            <h1 class="logo"><a href="${pageContext.request.contextPath}">Faboris</a></h1>
            <c:if test="${isCatalogPage}">
            <span>
                <form action="${pageContext.request.contextPath}" method="get">
                    <input
                        value="${searchValue}"
                        type="text"
                        id="search"
                        name="search"
                        placeholder="Поиск по названию"
                        max=50>
                    <button type="submit">Search</button>
                </form>
            </span>
            </c:if>
        </div>

            <nav class="menu">
                <span><a href="${pageContext.request.contextPath}/cart">Корзина</a></span>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <span class="profile">
                            <a href="${pageContext.request.contextPath}/auth">
                                <c:out value="${user.userName}" />
                            </a>
                        </span>
                    </c:when>
                    <c:otherwise>
                        <span class="profile"><a href="${pageContext.request.contextPath}/auth">Профиль</a></span>
                    </c:otherwise>
                </c:choose>
            </nav>
    </header>
</body>
</html>