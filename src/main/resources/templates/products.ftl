<#import "parts/common.ftl" as c>
<@c.page>
   <@c.menu>

    <div id="content" class="container">
        <div id="title">
            <h2>${header}</h2>
            <hr class="mb-3" style="border-color: #ccc" />
        </div>

        <div id="filters" class="row justify-content-around">
            <div class="col-auto">
                <span class="filter-title" id="inputColorName" onclick="showColorDropdown()">Цвет <i class="fas fa-angle-down"></i></span>
                <i class="fas fa-times" onclick="resetColor()" style="display: none"></i>
                <div class="color-filter-menu" style="display: none">
                    <#list colors as color>
                        <div class="color-menu-item">
                            <#if color.getCode() == "">
                                <div class="color-menu-item-box" style="background-image: url('/img/multicolor.png')"></div>
                            <#else>
                                <div class="color-menu-item-box" style="background-color: ${color.getCode()}"></div>
                            </#if>
                            <div class="color-menu-item-name">${color.getName()}</div>
                        </div>
                    </#list>
                </div>
            </div>
            <div class="col-auto">
                <span class="filter-on" onclick="sendFilters()">Применить фильтры</span>
            </div>
            <div class="col-auto">
                <span class="filter-title" id="inputPriceFilter" onclick="showPriceDropdown()">Цена <i class="fas fa-angle-down"></i></span>
                <i class="fas fa-times" onclick="resetPrice()" style="display: none"></i>
                <div class="price-filter-menu" style="display: none">
                    <div class="row">
                        <div class="col-12">
                            <div id="slider-range"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-5 pr-0">
                            <input type="number" id="minPrice" min="0" class="form-control" value="0"/>
                            <div class="after-form">₽</div>
                        </div>
                        <div class="col-2" style="font-size: 35px;line-height: 30px"> - </div>
                        <div class="col-5 pl-0">
                            <input type="number" id="maxPrice" min="0" class="form-control" value="100000"/>
                            <div class="after-form" style="margin-right: 15px">₽</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="goods mb-5 mt-4">
            <#list productVersions as productVersion>
                <div>
                <div class="card">
                    <input class="pv-id" type="hidden" value="${productVersion.id}" />
                    <div class="card-img-holder">
                        <img class="card-img card-img-top" src="upload\${productVersion.getMainImg()}">
                    </div>
                    <div class="card-body">
                        <h5 class="card-title uppercase">${productVersion.product.name}</h5>
                        <p class="card-text">${productVersion.product.generalInf}</p>
                        <h2 class="price">${productVersion.product.price} ₽</h2>
                        <div class="row product-btns">
                            <#if !user?? || !(user.hasAdminRole() || user.hasDeliveryRole())>
                                <div class="col-auto pr-2 card-btn">
                                    <div class="wish-btn" id='${productVersion.id}'>
                                        <span class="h-icon">
                                            <i class="far fa-heart"></i><br/>
                                            <i class='fas fa-heart' style="color: #d72828;"></i>
                                        </span>
                                    </div>
                                </div>
                                <div class="col pl-2 card-btn">
                                    <div class="basket-btn" onclick="productToCart(${productVersion.id})">
                                        <span class="b-icon"><i class="fas fa-shopping-basket"></i></span><br>В корзину
                                    </div>
                                </div>
                            </#if>
                        </div>
                    </div>
                </div>
                </div>
            </#list>

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
    <@c.script/>
</@c.page>