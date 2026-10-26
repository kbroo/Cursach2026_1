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
    <main class="product-card">
            <c:if test="${not empty product}">
                <div class="product-card-image-section">
                    <figure>
                        <img
                            src="${product.imageUrl}"
                            loading="lazy"><br>
                    </figure>
                    <div class="product-card-buy-buttons">
                        <form method="post" action="${pageContext.request.contextPath}/product/action">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="action" value="+">
                            <button type="submit">В корзину</button>
                        </form>

                        <c:if test="${quantityOfProductInCart != 0}">

                        <div class="product-card-items-in-cart">
                        <form method="post" action="${pageContext.request.contextPath}/product/action">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="action" value="-">
                            <button type="submit">-</button>
                        </form>
                        <span>
                            <c:out value="${quantityOfProductInCart}" />
                        </span>
                        <form method="post" action="${pageContext.request.contextPath}/product/action">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="action" value="+">
                            <button type="submit">+</button>
                        </form>
                        </div>

                        </c:if>
                    </div>
                    <div>
                        <c:if test="${not empty error}">
                            <span class="reg-error">
                                <c:out value="${error}" />
                            </span>
                        </c:if>
                    </div>
                </div>

                <div class="product-card-info-section">
                    <span class="product-card-info-description">
                        <b><c:out value="${product.name}" /></b>
                    </span><br>
                    <span class="product-card-info-description">
                        <c:out value="${product.description}" />
                    </span>
                    <div class="product-card-info-buy">
                        <span class="product-card-info-price">
                            <c:out value="${product.price}" /> BYN
                        </span>
                    </div>
                    <span class="product-card-info-stock">
                        На складе: <span><c:out value="${product.stock}" /></span>
                    </span>
                </div>
            </c:if>
        </div>
    </main>
</body>
</html>