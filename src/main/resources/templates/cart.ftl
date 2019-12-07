<#import "parts/common.ftl" as c>
<@c.page>
   <@c.menu>

   <div id="cart-content" class="container">
      <div id="title">
          <h2>Корзина</h2>
          <hr class="mb-5" style="border-color: #111111" />
      </div>
      <#list cartPositions>
          <#items as cartPosition>
             <div class="row cart-position mb-5 mx-0">
                <input type="hidden" value="${cartPosition.productVersion.id}" class="pv-id"/>
                <div class="cart-img-holder">
                   <img src="upload\${cartPosition.productVersion.getMainImg()}" class="cart-img"/>
                </div>
                <div class="cart-text">
                   <a href="/products/${cartPosition.productVersion.id}">
                       <span class="cart-name uppercase">${cartPosition.productVersion.product.name}</span>
                   </a>
                   <p>${cartPosition.productVersion.product.generalInf}</p>
                   <p>${cartPosition.productVersion.product.width} x ${cartPosition.productVersion.product.height} см</p>
                </div>
                <div class="cart-price">
                   ${cartPosition.productVersion.product.price}.-
                   <input type="hidden" value="${cartPosition.productVersion.product.price}" class="price-value"/>
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
                        <input type="number" name="count" onchange="changeProductCount(${cartPosition.productVersion.id}, this)" value="${cartPosition.count}" class="form-control input-count" min="1" max="${cartPosition.productVersion.count}" />
                    </div>
                </div>
             </div>
          </#items>
          <hr class="bounder"/>

          <div class="row mb-5 justify-content-between" id="cart-sum">
              <div class="col-auto">Сумма</div>
              <div class="col-auto">
                  <input type="hidden" id="sum-value" />
                  <span id="cart-sum-value"></span>.-
              </div>
          </div>

          <div id="order-form">
              <form id="productForm">
                  <div class="row">
                      <div class="col-auto">
                          <label for="inputArticle">Адрес доставки</label>
                      </div>
                  </div>
                  <div class="row mb-3">
                      <div class="col-4">
                          <input id="city" type="text" name="city" class="form-control" value="<#if user??><#if user.city??>${user.city}</#if></#if>"/>
                          <small class="form-text text-muted">Город*</small>
                      </div>
                      <div class="col-4">
                          <input id="street" type="text" name="street" class="form-control" value="<#if user??><#if user.street??>${user.street}</#if></#if>"/>
                          <small class="form-text text-muted">Улица*</small>
                      </div>
                      <div class="col-2">
                          <input id="house" type="text" name="house" class="form-control" value="<#if user??><#if user.house??>${user.house}</#if></#if>"/>
                          <small class="form-text text-muted">Дом*</small>
                      </div>
                      <div class="col-2">
                          <input type="text" name="apartment" class="form-control" value="<#if user??><#if user.apartment??>${user.apartment}</#if></#if>"/>
                          <small class="form-text text-muted">Квартира</small>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-auto">
                          <label for="inputArticle">Получатель</label>
                      </div>
                  </div>
                  <div class="row mb-3">
                      <div class="col-4">
                          <input id="surname" type="text" name="surname" class="form-control" value="<#if user??><#if user.surname??>${user.surname}</#if></#if>"/>
                          <small class="form-text text-muted">Фамилия*</small>
                      </div>
                      <div class="col-4">
                          <input id="firstname" type="text" name="firstname" class="form-control" value="<#if user??><#if user.firstname??>${user.firstname}</#if></#if>"/>
                          <small class="form-text text-muted">Имя*</small>
                      </div>
                      <div class="col-4">
                          <input type="text" name="patronymic" class="form-control" value="<#if user??><#if user.patronymic??>${user.patronymic}</#if></#if>"/>
                          <small class="form-text text-muted">Отчество</small>
                      </div>
                  </div>
                  <div class="row mb-2">
                      <div class="col-8">
                          <label for="inputArticle">Электронная почта*</label>
                          <input id="email" type="text" name="email" class="form-control" value="<#if user??><#if user.email??>${user.email}</#if></#if>"/>
                      </div>
                      <div class="col-4">
                          <label for="inputArticle">Телефон*</label>
                          <input id="phoneNumber" type="text" name="phoneNumber" class="form-control" value="<#if user??><#if user.phoneNumber??>${user.phoneNumber}</#if></#if>" />
                      </div>
                  </div>
                  <div class="row mb-4">
                      <div class="col-12">
                          <small class="form-text text-muted" style="font-size: 85%">* — обязательно для заполнения</small>
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

   <@c.script>
       <script>getCartSum();</script>
   </@c.script>
</@c.page>
