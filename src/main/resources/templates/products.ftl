<#import "parts/common.ftl" as c>
<@c.page "../">
   <@c.menu>

    <div id="content" class="container">
        <div class="goods mb-5">
            <#list products as product>
                <div>
                <div class="card">
                    <div class="card-img-holder">
                        <img class="card-img" src="upload\${product.getMainImg()}" class="card-img-top">
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">${product.generalInf}</p>
                        <h2 class="price">${product.price} ₽</h2>
                        <div class="row product-btns">
                            <div class="col pr-2">
                                <div class="wish-btn"><span class="h-icon"><i class="far fa-heart"></i></span><br>В пожелания</div>
                            </div>
                            <div class="col pl-2">
                                <div class="basket-btn" onclick="productToCart(${user.id}, ${product.id})"><span class="b-icon"><i class="fas fa-shopping-basket"></i></span><br>В корзину</div>
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