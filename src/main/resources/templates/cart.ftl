<#import "parts/common.ftl" as c>
<@c.page "../">
   <@c.menu>

   <div id="cart-content" class="container">
      <div id="title">
          <h2>Корзина</h2>
          <hr>
      </div>
      <#list products as product>
         <div class="row cart-position mb-5 mx-0">
            <div class="cart-img-holder">
               <img src="upload\${product.getMainImg()}" class="cart-img">
            </div>
            <div class="cart-text">
               <span class="cart-name">${product.name}</span>
               <p>${product.generalInf}</p>
               <p>${product.width} x ${product.height} см</p>
            </div>
            <div class="cart-price">
               ${product.price}.-
               <input type="hidden" value="${product.price}" class="price-value">
            </div>
            <div>
               <div class="cart-article">
                  ${product.article}
               </div>
            </div>
            <div class="justify-content-end" style="display: flex">
                <div class="delete-cart-position" onclick="deleteProductFromCart(${user.id}, ${product.id})">
                    <i class="fas fa-trash-alt"></i>
                </div>
                <div class="cart-count">
                    <input type="number" name="count" value="1" class="form-control" min="1" max="${product.count}">
                </div>
            </div>
         </div>
      </#list>

      <div class="row" id="cart-sum">
          <span id="cart-sum-value"></span>.-
      </div>
   </div>
   </@c.menu>
</@c.page>

<script>
    var sum = 0;
    $(".price-value").each(function() {
        sum += Number.parseInt($(this).val().replace(' ',''));
        console.log($(this).val().replace(' '));
    })
    $("#cart-sum-value").text(sum);
</script>