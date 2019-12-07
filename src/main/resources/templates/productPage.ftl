<#import "parts/common.ftl" as c>
<@c.page>
   <@c.menu>
       <div id="product-content" class="container">
           <div id="gallery-carousel" class="carousel slide" data-interval="false">
               <div class="carousel-inner">
                   <#list productVersion.images as image>
                       <div class="carousel-item">
                           <img src="/upload/${image}" class="d-block w-100">
                       </div>
                   </#list>
               </div>
               <a class="carousel-control-prev" href="#gallery-carousel" role="button" data-slide="prev">
                   <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                   <span class="sr-only">Previous</span>
               </a>
               <a class="carousel-control-next" href="#gallery-carousel" role="button" data-slide="next">
                   <span class="carousel-control-next-icon" aria-hidden="true"></span>
                   <span class="sr-only">Next</span>
               </a>
           </div>
           <#list productVersion.images as image>
               <div class="img-mini">
                   <img src="/upload/${image}" class="d-block w-100">
               </div>
           </#list>

           <div id="product-inf">
               <h4 class="uppercase">${productVersion.product.name}</h4>
               <p>${productVersion.product.generalInf}</p>
               <h2 class="price">${productVersion.product.price} ₽</h2>

               <#list productVersion.getOtherVersions()>
                   <div id="pv-menu">
                       <small class="form-text text-muted mt-5">Версия:</small>
                       <div id="product-version" onclick="productVersionMenu()">
                           <img src="/upload/${productVersion.getMainImg()}" />
                           <div class="ml-3">${productVersion.versionName}</div>
                           <div class="down-btn"><img src="/img/down.png" /></div>
                       </div>
                       <div id="product-version-menu">
                           <#items as pv>
                               <div class="product-version-menu-li" id="${pv.id}">
                                   <img src="/upload/${pv.getMainImg()}" />
                                   <div class="ml-3">${pv.versionName}</div>
                               </div>
                           </#items>
                       </div>
                   </div>
               <#else>
               </#list>

               <small class="form-text text-muted" style="margin-top: 140px">Артикул:</small>
               <div class="cart-article mt-1">${productVersion.article}</div>

               <#if !user?? || !(user.hasAdminRole() || user.hasDeliveryRole())>
                   <div id="page-basket-btn" onclick="productToCart(${productVersion.id})">
                       <span class="page-icon"><i class="fas fa-shopping-basket"></i></span>
                       <span>Добавить в корзину</span>
                   </div>
                   <div id="page-wish-btn" onclick="productToWishes(${productVersion.id})">
                       <span class="page-icon"><i class="far fa-heart"></i></span>
                       <span>Добавить в желания</span>
                   </div>
               <#elseif user.hasAdminRole()>
                   <a href="/admin/product/editor?article=${productVersion.article}">
                       <div id="page-edit-btn">
                           <span class="page-icon" style="margin-right: 30px"><i class="fas fa-pencil-alt"></i></span>
                           <span>Редактировать</span>
                       </div>
                   </a>
               </#if>
           </div>
       </div>

       <div class="product-inf container mt-5">
           <div class="product-props">
               <h4 style="font-weight: 500; margin-bottom: 1rem">Характеристики</h4>
               <hr class="mb-3" style="border-color: #ccc"/>
               <div style="font-size: 17px">
                   <div><b>Высота: </b> ${productVersion.product.height}см</div>
                   <div><b>Ширина: </b> ${productVersion.product.width}см</div>
                   <div class="mb-3"><b>Глубина: </b> ${productVersion.product.depth}см</div>
                   <#list productVersion.product.values as value>
                       <div><b>${value.property.name}: </b> ${value.value}</div>
                   </#list>
               </div>
           </div>
           <div class="product-description">
               <h4 style="font-weight: 500; margin-bottom: 1rem">Описание</h4>
               <hr class="mb-3" style="border-color: #ccc"/>
               <p style="white-space: pre-line">${productVersion.product.description}</p>
           </div>
       </div>

       <div id="fog">
           <div id="window">
               <span style="font-size: large" id="window-message"></span>
               <div class="row justify-content-between" style="position: relative; top: 47%">
                   <div class="btn ml-3 continue-btn" onclick="closeDialog()">Продолжить</div>
                   <a href="/cart">
                       <div class="btn mr-3 to-cart-btn">
                           <span id="ref-btn">В корзину</span>
                           <i class="fas fa-arrow-right" style="margin-left: 10px"></i>
                       </div>
                   </a>
               </div>
           </div>
           <div id="wishes-login-window">
               <span style="font-size: large" id="window-message">
                   Войдите, чтобы добавлять товары в<br/><i class="far fa-heart"></i>&nbsp;Список желаний
               </span>
               <div class="row justify-content-between" style="position: relative; top: 30%">
                   <div class="btn ml-3" id="win-delete-btn" onclick="closeDialog()">Отмена</div>
                   <a href="/login">
                       <div class="btn mr-3 to-cart-btn">
                           <span id="ref-btn">Войти</span>
                       </div>
                   </a>
               </div>
           </div>
       </div>
   </@c.menu>
   <@c.script>
       <script>
           $(".carousel-item:first").addClass("active");
           $(".img-mini:first").css("box-shadow", "0 0 0 3px rgba(0,0,0,0.3)");
       </script>
   </@c.script>
</@c.page>
