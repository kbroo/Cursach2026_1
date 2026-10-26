<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/style.css">
<head>
    <title>FABORIS</title>
</head>
<body>
    <%@ include file="WEB-INF/views/header.jsp" %>
    <main>
            <c:if test="${not empty error}">
                <div class="not-found-elements">
                    <span>${error}</span>
                </div>
            </c:if>
        <div class="products-main-section">
            <c:if test="${not empty products}">
                <c:forEach var="product" items="${products}">
                    <div>
                        <figure>
                            <img
                                src="${product.imageUrl}"
                                loading="lazy"><br>
                            <caption>${product.name}</caption>
                        </figure>
                        <span>
                            <c:out value="${product.price}" /> BYN
                        </span><br>
                        <span>
                            <a href="${pageContext.request.contextPath}/product?id=${product.id}">Подробнее</a>
                        </span>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </main>
</body>
</html>