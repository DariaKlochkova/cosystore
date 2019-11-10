<#macro page lvl>
    <!doctype html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="csrf-token" content="${_csrf.token}">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="${lvl}static/styles.css">

        <title>CosyStore</title>
    </head>
    <body>
        <nav id="lk" class="navbar navbar-expand-lg">
            <a id="userName" class="mr-auto" href="#">
                <#if user??>
                    <i class="far fa-user"></i> ${user.fullname}
                </#if>
            </a>
            <a class="upper-nav-item" href="#"><i class="far fa-heart"></i> Список желаний</a>
            <a class="upper-nav-item" href="/cart"><i class="fas fa-shopping-basket"></i> Корзина</a>
            <a class="upper-nav-item" href="#" style="margin-left: 30px; color: #666;">Выйти</a>
        </nav>
    <#nested>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/3.3.4/jquery.inputmask.bundle.min.js"></script>
        <script src="${lvl}static/script.js"></script>
        <script src="${lvl}static/validation.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
    </body>
    </html>
</#macro>


<#macro admin header>
    <div class="sidenav">
        <span>Товары</span>
        <a href="/admin/product" class="li">Добавить</a>
        <div class="li" id="edit">Редактировать
            <div id="edit-window">
                <form action="/admin/product/version" method="get" id="article-form">
                    <label for="inputProductArticle">Введите артикул:</label>
                    <input type="text" name="article" id="inputProductArticle" class="form-control mb-2 mt-1">
                    <button class="btn" id="edit-find-btn" onclick="openProductEditor()">Найти</button>
                </form>
            </div>
        </div>
        <a href="/admin/categories" style="margin-top: 10px;">Категории</a>
        <a href="#">Скидки</a>
        <a href="#">Акции</a>
        <a href="#">Баннеры</a>
        <a href="#" style="font-size: 30px; margin-top: 20px;">Заказы</a>
        <img src="/img/home_cut.png" width="120px" style="position: fixed; left: 0; bottom: 0;">
    </div>
    <div class="main">
        <div id="edit-fog"></div>
        <div class="admin-panel-h1">
            <h2>${header}</h2>
        </div>
        <div class="admin-panel">
        <#nested>
        </div>
    </div>
</#macro>


<#macro menu>
    <nav class="navbar navbar-expand-lg" id="main-navbar">
        <div class="container">
            <a class="navbar-brand" href="/">CosySt<img src="img\home.png" height="26px" width="20px">re</a>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown" style="margin-right: 20px;">
                    <div class="nav-link dropdown-toggle" id="product-menu-btn" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Товары
                        <div id="product-menu" style="display: none">
                            <div id="product-menu-groups">
                                <#list categoryGroups as categoryGroup>
                                    <span class="dropdown-item">${categoryGroup.getName()}</span>
                                </#list>
                            </div>
                            <#list categoryGroups as categoryGroup>
                                <div class="product-menu-categories">
                                    <#list categoryGroup.getCategories() as category>
                                        <a class="product-menu-categories-item" href="/products?category=${category.id}">${category.name}</a>
                                    </#list>
                                </div>
                            </#list>
                        </div>
                    </div>
                </li>
                <li class="nav-item dropdown" style="margin-right: 20px;">
               <span class="nav-link dropdown-toggle" id="navDrop2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Комнаты
               </span>
                    <div class="dropdown-menu" aria-labelledby="navDrop2">
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Цена ниже</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" id="search">
                <input class="form-control mr-sm-2" id="search-input" type="search" placeholder="Что вы ищете?" aria-label="Search">
                <button class="btn my-2 my-sm-0 py-0" type="submit"><i class="material-icons" style="line-height: 1.5">&#xe8b6;</i></button>
            </form>
        </div>
    </nav>
    <#nested>

</#macro>