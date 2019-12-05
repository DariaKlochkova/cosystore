<#macro page>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <meta name="csrf-token" content="${_csrf.token}"/>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/static/styles.css"/>

        <title>CosyStore</title>
    </head>
    <body>
    <#nested>
    </body>
</html>
</#macro>

<#macro script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/3.3.4/jquery.inputmask.bundle.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="/static/script.js"></script>
    <script src="/static/validation.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
    <#nested>
</#macro>


<#macro admin header>
    <nav id="upper-nav" class="navbar navbar-expand-lg">
        <a id="userName" class="mr-auto" style="color: #111">
            <i class="far fa-user"></i> ${user.surname} ${user.firstname}
        </a>
        <a id="admin-btn-on" href="/admin/product" class="hovered">
            Администрирование
        </a>
        <span class="upper-nav-item log" onclick="logoutDialog()">Выйти</span>
    </nav>
    <div class="sidenav">
        <span>Товары</span>
        <a href="/admin/product" class="li">Добавить</a>
        <div class="li" id="edit">Редактировать
            <div id="edit-window">
                <form action="/admin/product/version" method="get" id="article-form">
                    <label for="inputProductArticle">Введите артикул:</label>
                    <input type="text" name="article" id="inputProductArticle" class="form-control mb-2 mt-1" />
                    <button class="btn" id="edit-find-btn" onclick="openProductEditor()">Найти</button>
                </form>
            </div>
        </div>
        <a href="/admin/categories" style="margin-top: 10px;">Категории</a>
        <a href="#">Скидки</a>
        <a href="#">Акции</a>
        <a href="#">Баннеры</a>
        <a href="/"><img src="/img/home_cut.png" width="120px" style="position: fixed; left: 0; bottom: 0;" /></a>
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
    <div id="logout-fog">
        <div id="logout-window">
            <span style="font-size: large">Вы уверены, что хотите выйти?</span>
            <div class="row justify-content-between" style="position: relative; top: 47%">
                <div class="btn ml-3 continue-btn" onclick="closeLogoutDialog()">Отмена</div>
                <div class="btn mr-3 to-cart-btn" onclick="logout()">Да</div>
            </div>
        </div>
    </div>
</#macro>


<#macro delivery header>
    <nav id="upper-nav" class="navbar navbar-expand-lg">
        <a id="userName" class="mr-auto" style="color: #111">
            <i class="far fa-user"></i> ${user.surname} ${user.firstname}
        </a>
        <a id="admin-btn-on" href="/orders">
            Заказы
        </a>
        <span class="upper-nav-item log" onclick="logoutDialog()">Выйти</span>
    </nav>
    <div class="sidenav">
        <a href="/orders">Заказы</a>
        <#list statuses as status>
            <a href="/orders?status=${status.code}" class="li">${status.getName()}</a>
        </#list>
        <a href="/"><img src="/img/home_cut.png" width="120px" style="position: fixed; left: 0; bottom: 0;" /></a>
    </div>
    <div class="main">
        <div class="admin-panel-h1">
            <h2>${header}</h2>
        </div>
        <div class="admin-panel">
            <#nested>
        </div>
    </div>
    <div id="logout-fog">
        <div id="logout-window">
            <span style="font-size: large">Вы уверены, что хотите выйти?</span>
            <div class="row justify-content-between" style="position: relative; top: 47%">
                <div class="btn ml-3 continue-btn" onclick="closeLogoutDialog()">Отмена</div>
                <div class="btn mr-3 to-cart-btn" onclick="logout()">Да</div>
            </div>
        </div>
    </div>
</#macro>


<#macro menu>
    <nav id="upper-nav" class="navbar navbar-expand-lg">
        <#if user??>
            <a id="userName" class="mr-auto" href="/lk">
                <i class="far fa-user"></i> ${user.surname} ${user.firstname}
            </a>
            <#if user.hasAdminRole()>
                <a id="admin-btn" href="/admin/product">
                    Администрирование
                </a>
            </#if>
            <#if user.hasDeliveryRole()>
                <a id="admin-btn" href="/orders">
                    Заказы
                </a>
            </#if>
        </#if>
        <#if !user?? || !(user.hasAdminRole() || user.hasDeliveryRole())>
            <a class="upper-nav-item" href="/wishes" style="margin-left: auto; margin-right: 30px"><i class="far fa-heart"></i> Список желаний</a>
            <a class="upper-nav-item" href="/cart" style="margin-right: 100px"><i class="fas fa-shopping-basket"></i> Корзина</a>
        </#if>
        <#if user??>
            <span class="upper-nav-item log" onclick="logoutDialog()">Выйти</span>
        <#else>
            <a class="upper-nav-item log" href="/login">Войти</a>
        </#if>
    </nav>
    <nav class="navbar navbar-expand-lg" id="main-navbar">
        <div class="container">
            <a class="navbar-brand" href="/">CosySt<img src="/img/home.png" height="26px" width="20px"/>re</a>
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
                    <div class="nav-link dropdown-toggle" id="product-menu-btn" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Комнаты
                        <div id="room-menu" style="display: none">
                            <#list rooms as room>
                                <div class="room-item">${room.getName()}</div>
                            </#list>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Цена ниже</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" id="search" action="/search">
                <input name="query" class="form-control mr-sm-2" id="search-input" type="search" placeholder="Что вы ищете?" aria-label="Search" autocomplete="off" />
                <button class="btn my-2 my-sm-0 py-0" type="submit"><i class="material-icons" style="line-height: 1.5">&#xe8b6;</i></button>
            </form>
        </div>
    </nav>
    <#nested>
    <div id="logout-fog">
        <div id="logout-window">
            <span style="font-size: large">Вы уверены, что хотите выйти?</span>
            <div class="row justify-content-between" style="position: relative; top: 47%">
                <div class="btn ml-3 continue-btn" onclick="closeLogoutDialog()">Отмена</div>
                <div class="btn mr-3 to-cart-btn" onclick="logout()">Да</div>
            </div>
        </div>
    </div>
</#macro>