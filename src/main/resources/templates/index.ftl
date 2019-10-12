<#import "parts/common.ftl" as c>
<@c.page "../" "Клочкова Дарья">
   <nav class="navbar navbar-expand-lg">
      <div class="container">
         <a class="navbar-brand" href="index.ftl">CosySt<img src="img\home.png" height="26px" width="20px">re</a>
         <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown" style="margin-right: 20px;">
               <span class="nav-link dropdown-toggle" id="navDrop1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Товары
               </span>
               <div class="dropdown-menu" aria-labelledby="navDrop1">
                  <a class="dropdown-item" href="#">Стеллажи и серванты</a>
                  <a class="dropdown-item" href="#">Кровати</a>
                  <a class="dropdown-item" href="#">Кухонные шкафы</a>
                  <a class="dropdown-item" href="#">Столы и стулья</a>
                  <a class="dropdown-item" href="#">Мягкая мебель</a>
                  <a class="dropdown-item" href="#">Хранение одежды</a>
                  <a class="dropdown-item" href="#">Текстиль и ковры</a>
                  <a class="dropdown-item" href="#">Зеркала и аксессуары</a>
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

   <div id="content" class="container">
      <div id="carousel" class="carousel slide" data-ride="carousel">
         <ol class="carousel-indicators">
            <li data-target="#carousel" data-slide-to="0" class="active"></li>
            <li data-target="#carousel" data-slide-to="1"></li>
            <li data-target="#carousel" data-slide-to="2"></li>
         </ol>
         <div class="carousel-inner">
            <div class="carousel-item active">
               <img src="img\Sofa.jpg" class="d-block w-100">
            </div>
            <div class="carousel-item">
               <img src="img\Carpet.jpg" class="d-block w-100">
            </div>
            <div class="carousel-item">
               <img src="img\Table.jpg" class="d-block w-100">
            </div>
         </div>
      </div>
   </div>
</@c.page>