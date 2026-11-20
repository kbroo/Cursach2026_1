<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<head>
    <title>FABORIS</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <main class="checkout">
        <div class="checkout-container">
            <c:if test="${not empty cart}">
                <div>
                    <b>Ваш заказ состоит из:</b><br><br>
                    <c:forEach var="item" items="${cart.items}">
                        – <c:out value="${item.productName}" />
                        <c:out value="${item.quantity}" /> Шт<br>
                        <c:out value="${item.productPrice}" /> BYN<br><br>
                    </c:forEach>
                    <span>Итоговая цена: <c:out value="${cart.totalPrice}" /> BYN</span><br><br>
                </div>
                <form method="post" action="${pageContext.request.contextPath}/cart/checkout-check">
                    <legend>Адрес доставки:</legend>
                    <input
                        name="address"
                        type="text"
                        max=255
                        placeholder="Обл. г. ул. дом/корпус"
                        required>
                    <br><br>
                    <c:if test="${not empty checkoutError}">
                        <span>
                            <c:out value="${checkoutError}" />
                        </span>
                    </c:if>
                    <button type="submit">Оформить</button>
                </form>
            </c:if>
        </div>
    </main>
</body>
</html>