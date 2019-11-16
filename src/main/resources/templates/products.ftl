<#import "parts/common.ftl" as c>
<@c.page "../">
   <@c.menu>

    <div id="content" class="container">
        <div class="goods mb-5">
            <#list productVersions as productVersion>
                <div>
                <div class="card">
                    <div class="card-img-holder">
                        <img class="card-img" src="upload\${productVersion.getMainImg()}" class="card-img-top">
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${productVersion.product.name}</h5>
                        <p class="card-text">${productVersion.product.generalInf}</p>
                        <h2 class="price">${productVersion.product.price} ₽</h2>
                        <div class="row product-btns">
                            <div class="col pr-2">
                                <div class="wish-btn"><span class="h-icon"><i class="far fa-heart"></i></span><br>В пожелания</div>
                            </div>
                            <div class="col pl-2">
                                <div class="basket-btn" onclick="productToCart(${user.id}, ${productVersion.id})"><span class="b-icon"><i class="fas fa-shopping-basket"></i></span><br>В корзину</div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </#list>

        </div>
    </div>

    </@c.menu>
</@c.page>