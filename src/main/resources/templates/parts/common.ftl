<#macro page lvl username>
    <!doctype html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="${lvl}static/styles.css">

        <title>CosyStore</title>
    </head>
    <body>
        <nav id="lk" class="navbar navbar-expand-lg">
            <a id="userName" class="mr-auto" href="#"><i class="far fa-user"></i> ${username}</a>
            <a class="upper-nav-item" href="#"><i class="far fa-heart"></i> Список желаний</a>
            <a class="upper-nav-item" href="#"><i class="fas fa-shopping-basket"></i> Корзина</a>
            <a class="upper-nav-item" href="#" style="margin-left: 30px; color: #666;">Войти</a>
        </nav>
    <#nested>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="${lvl}static/script.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
    </body>
    </html>
</#macro>


<#macro admin header>
    <div class="sidenav">
        <span>Товары</span>
        <a href="/admin/add" class="li">Добавить</a>
        <a href="#" class="li">Редактировать</a>
        <a href="/admin/categories" style="margin-top: 10px;">Категории</a>
        <a href="#">Скидки</a>
        <a href="#">Акции</a>
        <a href="#">Баннеры</a>
        <a href="#" style="font-size: 30px; margin-top: 20px;">Заказы</a>
        <img src="/img/home_cut.png" width="120px" style="position: fixed; left: 0; bottom: 0;">
    </div>
    <div class="main">
        <div class="admin-panel-h1">
            <h2>${header}</h2>
        </div>
        <div class="admin-panel">
        <#nested>
        </div>
    </div>
</#macro>