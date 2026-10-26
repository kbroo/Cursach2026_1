<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<link rel="stylesheet" href = "${pageContext.request.contextPath}/css/style.css">
<head>
    <title>FABORIS</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <main class="cart">
        <h1>Корзина товаров</h1>

        <div class="cart-content">

        <div class="cart-items">
            <c:if test="${empty cart.items}">
                <div class="empty-cart">
                    Корзина пуста<br>
                    <span>
                        <a href="${pageContext.request.contextPath}">За покупками</a>
                    </span>
                </div>
            </c:if>

            <c:if test="${not empty cart.items}">
                <c:forEach var="item" items="${cart.items}">
                    <div class="cart-item">
                        <figure>
                            <img
                                src="${item.imageUrl}"
                                loading="lazy"><br>
                        </figure>
                        <div class="cart-item-info">
                            <span class= "cart-item-name">
                                <c:out value="${item.productName}" />
                            </span>
                            <span class= "cart-item-quantity">
                                <c:out value="${item.quantity}" /> Шт.
                            </span>
                            <span class= "cart-item-price">
                                <c:out value="${item.productPrice}" /> BYN
                            </span>
                        </div>
                        <div class="cart-item-action">
                            <form method="post" action="${pageContext.request.contextPath}/cart/action">
                                <input type="hidden" name="productId" value="${item.productId}">
                                <input type="hidden" name="action" value="+">
                                <button type="submit">+</button>
                            </form>
                            <form method="post" action="${pageContext.request.contextPath}/cart/action">
                                <input type="hidden" name="productId" value="${item.productId}">
                                <input type="hidden" name="action" value="-">
                                <button type="submit">-</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>

        <c:if test="${not empty cart}">
            <div class="cart-items-total-price">
                <span>
                    Итоговая стоимость:
                </span><br>
                <span class= "cart-item-total-price">
                    <c:out value="${cart.totalPrice}" /> BYN
                </span><br>
            </div>
        </c:if>

        </div>
    </main>
</body>
</html>