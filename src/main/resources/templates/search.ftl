<#import "parts/common.ftl" as c>
<@c.page>
   <@c.menu>

    <div id="content" class="container">
        <div id="title">
            <h2>${header}</h2>
            <hr class="mb-5" style="border-color: #ccc" />
        </div>
        <#list productVersions>
            <div class="goods mb-5">
                <#items as productVersion>
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
                                    <div class="wish-btn" id='${productVersion.id}'>
                                        <span class="h-icon">
                                            <i class="far fa-heart"></i><br/>
                                            <i class='fas fa-heart' style="color: #d72828;"></i>
                                        </span>
                                    </div>
                                </div>
                                <div class="col pl-2 card-btn">
                                    <div class="basket-btn" onclick="productToCart(${productVersion.id})">
                                        <span class="b-icon"><i class="fas fa-shopping-basket"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </#items>
            </div>
        <#else>
            <p class="empty">Ничего не найдено</p>
        </#list>

        </div>
    </div>
    </@c.menu>
    <@c.script />
</@c.page>