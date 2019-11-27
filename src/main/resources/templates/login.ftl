<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Вход</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
    <link rel="stylesheet" href="/static/styles.css"/>
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" action="/login">
        <div class="row justify-content-between">
            <div class="col-auto">
                <h2 class="form-signin-heading" style="margin-top: 40px">Вход</h2>
            </div>
            <div class="col-auto" style="display: none">
                <img src="/img/home.png" width="80px"/>
            </div>
        </div>
        <p>
            <label for="username" class="sr-only">Логин</label>
            <input type="text" id="username" name="username" class="form-control login-input" placeholder="Логин" required autofocus>
        </p>
        <p>
            <label for="password" class="sr-only">Пароль</label>
            <input type="password" id="password" name="password" class="form-control login-input" placeholder="Пароль" required>
        </p>
        <p><input type='checkbox' name='remember-me'/> Запомнить меня</p>
        <input name="_csrf" type="hidden" value="${_csrf.token}" />
        <button class="btn btn-lg btn-block" type="submit">Войти</button>
    </form>
</div>
</body></html>