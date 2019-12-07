<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Регистрация</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
    <link rel="stylesheet" href="/static/styles.css"/>
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" action="/registration">
        <div class="row">
            <div class="col-auto">
                <h2 class="form-signin-heading" style="margin-top: 40px">Регистрация</h2>
            </div>
        </div>
        <#if error??>
            <p class="error">Такой логин уже существует</p>
        </#if>
        <p>
            <label for="firstname" class="sr-only">Имя</label>
            <input type="text" id="firstname" name="firstname" class="form-control login-input" placeholder="Имя" required autofocus>
        </p>
        <p class="mb-5">
            <label for="surname" class="sr-only">Фамилия</label>
            <input type="text" id="surname" name="surname" class="form-control login-input" placeholder="Фамилия" required>
        </p>
        <p>
            <label for="username" class="sr-only">Логин</label>
            <input type="text" id="username" name="username" class="form-control login-input" placeholder="Логин" required>
        </p>
        <p>
            <label for="password" class="sr-only">Пароль</label>
            <input type="password" id="password" name="password" class="form-control login-input" placeholder="Пароль" required>
<#--            <div class="after-form"><i class="far fa-eye after-form"></i>-->
        </p>
        <p class="mb-5">
            <label for="password-2" class="sr-only">Повторите пароль</label>
            <input type="password" id="password-2" name="password-2" class="form-control login-input" placeholder="Повторите пароль" required>
        </p>
        <input name="_csrf" type="hidden" value="${_csrf.token}" />
        <button class="btn btn-lg btn-block" type="submit">Зарегистрироваться</button>
        <a href="/login"><p class="register">Вход</p></a>
    </form>
</div>
</body></html>