<#import "parts/common.ftl" as c>
<@c.page>
   <@c.menu>

    <div id="content" class="container">
        <div id="title">
            <h2>${header}</h2>
            <hr class="mb-5" style="border-color: #ccc" />
        </div>
        <div class="goods mb-5">
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
                            <div class="col-auto pr-2 card-btn">
                                <div class="wish-btn" onclick="productToWishes(${productVersion.id})">
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
    </div>
    </@c.menu>
    <@c.script/>
</@c.page>