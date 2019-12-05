<#import "parts/common.ftl" as c>
<@c.page>
   <@c.menu>

    <div id="content" class="container">
        <div id="title">
            <h2>Список желаний</h2>
            <hr class="mb-5" style="border-color: #ccc" />
        </div>

        <#list wishList>
            <div class="wishes mb-5">
                <#items as productVersion>
                <div class="card">
                    <input class="pv-id" type="hidden" value="${productVersion.id}" />
                    <div class="card-img-holder">
                        <img class="card-img card-img-top" src="upload\${productVersion.getMainImg()}">
                    </div>
                    <div class="card-body">
                        <h5 class="card-title uppercase">${productVersion.product.name}</h5>
                        <p class="card-text" style="font-size: 13px">${productVersion.product.generalInf}</p>
                        <h4 class="price" style="font-weight: 500">${productVersion.product.price} ₽</h4>
                        <div class="row product-btns">
                            <div class="col-auto pr-1 card-btn">
                                <div class="wish-btn-mini" id="${productVersion.id}"><i class="far fa-trash-alt"></i></span></div>
                            </div>
                            <div class="col pl-2 card-btn">
                                <div class="basket-btn" onclick="productToCart(${productVersion.id})" style="font-size: 17px">
                                    <span style="line-height: 36px"><i class="fas fa-shopping-basket"></i></span> В корзину
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </#items>
            </div>
        <#else>
            <p class="empty">Ваш список желаний пуст</p>
        </#list>

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