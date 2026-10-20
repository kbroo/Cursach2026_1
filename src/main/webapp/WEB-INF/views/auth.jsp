<%@ page contentType="text/html; charset=UTF-8" language="java"%>
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
    <main class="auth-register">
    <form method="post" action="/home" class="auth-form">
        <fieldset>
            <legend>Укажите данные для входа:</legend>
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
                <label for="password">Пароль:</label><br>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        autocomplete=on
                        required>
            </div><br>

            <button type="submit">Войти</button><br><br>
            <span>Еще нет аккаунта?</span><br>
            <a href="${pageContext.request.contextPath}/auth/registration">Зарегистрироваться</a>

        </fieldset>
    </form>
    </main>
</body>
</html>