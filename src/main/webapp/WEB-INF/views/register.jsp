<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>FABORIS</title>
</head>
<body>
    <%@ include file="header.jsp" %>

    <main class="auth-register">
    <form method="post" action="${pageContext.request.contextPath}/auth/check-registration" class="auth-form">
        <fieldset>
            <legend>Укажите данные для регистрации:</legend>
            <div class="form-group">
                <label for="username">Логин:</label><br>
                <input
                    type="text"
                    id="username"
                    name="username"
                    autocomplete=on
                    autofocus=on
                    required>
            </div>

            <div class="form-group">
                <label for="email">Почта:</label><br>
                <input
                    type="email"
                    id="email"
                    name="email"
                    autocomplete=on
                    required>
            </div>

            <div class="form-group">
                <label for="password">Пароль:</label><br>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        autocomplete=on
                        required>
            </div><br>

            <c:if test="${not empty error}">
                <span class="reg-error">${error}</span><br>
            </c:if>

            <button type="submit">Зарегистрироваться</button><br><br>
            <span>Уже есть аккаунт?</span><br>
            <a href="${pageContext.request.contextPath}/auth">Войти</a>

        </fieldset>
    </form>
    </main>
</body>
</html>