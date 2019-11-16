<#import "parts/common.ftl" as c>
<@c.page "../">
   <@c.menu>

   <div id="cart-content" class="container">
      <div id="title">
          <h2>Корзина</h2>
          <hr class="mb-5" style="border-color: #111111">
      </div>
      <#list cartPositions>
          <#items as cartPosition>
             <div class="row cart-position mb-5 mx-0">
                <input type="hidden" value="${cartPosition.productVersion.id}" class="pv-id">
                <div class="cart-img-holder">
                   <img src="upload\${cartPosition.productVersion.getMainImg()}" class="cart-img">
                </div>
                <div class="cart-text">
                   <span class="cart-name">${cartPosition.productVersion.product.name}</span>
                   <p>${cartPosition.productVersion.product.generalInf}</p>
                   <p>${cartPosition.productVersion.product.width} x ${cartPosition.productVersion.product.height} см</p>
                </div>
                <div class="cart-price">
                   ${cartPosition.productVersion.product.price}.-
                   <input type="hidden" value="${cartPosition.productVersion.product.price}" class="price-value">
                </div>
                <div>
                   <div class="cart-article">
                      ${cartPosition.productVersion.article}
                   </div>
                </div>
                <div class="justify-content-end" style="display: flex">
                    <div class="delete-cart-position" onclick="deleteProductFromCart(${cartPosition.productVersion.id})">
                        <i class="fas fa-trash-alt"></i>
                    </div>
                    <div class="cart-count">
                        <input type="number" name="count" onchange="changeProductCount(${cartPosition.productVersion.id}, this)" value="${cartPosition.count}" class="form-control input-count" min="1" max="${cartPosition.productVersion.count}">
                    </div>
                </div>
             </div>
          </#items>
          <hr class="bounder">

          <div class="row mb-5 justify-content-between" id="cart-sum">
              <div class="col-auto">Сумма</div>
              <div class="col-auto">
                  <input type="hidden" id="sum-value">
                  <span id="cart-sum-value"></span>.-
              </div>
          </div>

          <div id="order-form">
              <form id="productForm">
                  <div class="row mb-3">
                      <div class="col-auto">
                          <label for="inputArticle">Адрес доставки</label>
                      </div>
                      <div class="col">
                          <input type="text" name="address" id="inputAddress" class="form-control" value="<#if user??><#if user.address??>${user.address}</#if></#if>">
                      </div>
                  </div>
                  <div class="row mb-3">
                      <div class="col-auto">
                          <label for="inputArticle">Электронная почта</label>
                      </div>
                      <div class="col">
                          <input type="email" name="email" id="inputEmail" class="form-control" value="<#if user??><#if user.email??>${user.email}</#if></#if>">
                      </div>
                  </div>
                  <div class="row mb-5">
                      <div class="col-8">
                          <label for="inputArticle">Получатель</label>
                          <input type="text" name="recipient" id="inputRecipient" class="form-control" value="<#if user??>${user.fullname}</#if>">
                      </div>
                      <div class="col-4">
                          <label for="inputArticle">Телефон</label>
                          <input type="text" name="phone" id="inputPhone" class="form-control" value="<#if user??><#if user.phoneNumber??>${user.phoneNumber}</#if></#if>">
                      </div>
                  </div>
              </form>
          </div>


          <div class="row mb-3 justify-content-center" id="cart-sum">
              <div class="col-auto">
                  <div id="order-btn" onclick="openOrderForm()">Оформить заказ</div>
              </div>
          </div>
      <#else>
          <p class="empty">Ваша корзина пуста</p>
      </#list>

   </div>
   </@c.menu>
</@c.page>

<script>
    getCartSum();
</script>