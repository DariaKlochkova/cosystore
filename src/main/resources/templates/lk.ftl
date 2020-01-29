<#import "parts/common.ftl" as c>
<@c.page>
   <@c.menu>

   <div class="container" id="#cart-content">
       <div id="lk-data">
           <div class="row">
               <div class="col-3" style="text-align: center">
                  <i class="far fa-user" style="font-size: 90px;"></i>
               </div>
               <div class="col-9">
                   <h2 class="mb-0">${user.surname} ${user.firstname} <#if user.patronymic??>${user.patronymic}</#if></h2><br/>
                   <span>Телефон</span><br/>
                   <#if user.phoneNumber??>
                       ${user.phoneNumber}
                   <#else>
                       <span style="color: #999">не указан</span>
                   </#if>
                   <br/><br/>
                   <span>Email</span><br/>
                   <#if user.email??>
                       ${user.email}
                   <#else>
                       <span style="color: #999">не указан</span>
                   </#if>
                   <br/><br/>
                   <span>Адрес доставки</span><br/>
                   <#if user.city??>
                       г. ${user.city}, ул. ${user.street}, д. ${user.house}<#if user.apartment??>, кв. ${user.apartment}</#if>
                   <#else>
                       <span style="color: #999">не указан</span>
                   </#if>
                   <div class="row justify-content-end mt-4">
                       <div class="col-auto">
                           <div class="lk-edit-btn" onclick="openLkEdit()">
                               <i class="fas fa-pencil-alt"></i>
                               Редактировать
                           </div>
                       </div>
                   </div>
               </div>
           </div>
       </div>

       <div id="lk-data-edit" style="display: none">
           <form method="post" action="/lk" id="lk-data-form">
               <input type="hidden" name="_csrf" value="${_csrf.token}"/>
               <input type="hidden" name="id" value="${user.id}"/>
               <div class="row">
                   <div class="col-auto">
                       <label>ФИО пользователя</label>
                   </div>
               </div>
               <div class="row mb-3">
                   <div class="col-4">
                       <input id="surname" type="text" name="surname" class="form-control" value="<#if user.surname??>${user.surname}</#if>"/>
                       <small class="form-text text-muted">Фамилия*</small>
                   </div>
                   <div class="col-4">
                       <input id="firstname" type="text" name="firstname" class="form-control" value="<#if user.firstname??>${user.firstname}</#if>"/>
                       <small class="form-text text-muted">Имя*</small>
                   </div>
                   <div class="col-4">
                       <input id="patronymic" type="text" name="patronymic" class="form-control" value="<#if user.patronymic??>${user.patronymic}</#if>"/>
                       <small class="form-text text-muted">Отчество</small>
                   </div>
               </div>
               <div class="row">
                   <div class="col-auto">
                       <label>Адрес доставки</label>
                   </div>
               </div>
               <div class="row mb-3">
                   <div class="col-4">
                       <input id="city" type="text" name="city" class="form-control" value="<#if user.city??>${user.city}</#if>"/>
                       <small class="form-text text-muted">Город*</small>
                   </div>
                   <div class="col-4">
                       <input id="street" type="text" name="street" class="form-control" value="<#if user.street??>${user.street}</#if>"/>
                       <small class="form-text text-muted">Улица*</small>
                   </div>
                   <div class="col-2">
                       <input id="house" type="text" name="house" class="form-control" value="<#if user.house??>${user.house}</#if>"/>
                       <small class="form-text text-muted">Дом*</small>
                   </div>
                   <div class="col-2">
                       <input id="apartment" type="text" name="apartment" class="form-control" value="<#if user.apartment??>${user.apartment}</#if>"/>
                       <small class="form-text text-muted">Квартира</small>
                   </div>
               </div>
               <div class="row mb-2">
                   <div class="col-8">
                       <label>Электронная почта*</label>
                       <input id="email" type="text" name="email" class="form-control" value="<#if user??><#if user.email??>${user.email}</#if></#if>"/>
                   </div>
                   <div class="col-4">
                       <label>Телефон*</label>
                       <input type="text" name="phoneNumber" class="form-control" value="<#if user??><#if user.phoneNumber??>${user.phoneNumber}</#if></#if>"/>
                   </div>
               </div>
               <div class="row mb-4">
                   <div class="col-8">
                       <small class="form-text text-muted" style="font-size: 85%">* — обязательно для заполнения</small>
                   </div>
               </div>
               <div class="row justify-content-between px-3">
                   <div class="cancel-order col-auto" onclick="closeLkEdit()">Отмена</div>
                   <div class="lk-edit-btn col-auto" onclick="checkUserData('${user.username}', '${user.password}')">Сохранить изменения</div>
               </div>
           </form>
       </div>


       <div style="margin: 60px auto; width: 720px">
           <h3>Мои заказы</h3>
           <hr class="mb-5" style="border-color: #111"/>
           <#list orders as order>
               <div class="lk-order">
                   <div class="row">
                       <div class="col-3">
                           <span style="color: #999">Дата заказа:</span><br/><span style="font-size: 21px">${order.date}</span>
                       </div>
                       <div class="col-6 lk-order-positions">
                           <div class="row" style="color: #999"><div class="col">Позиции заказа:</div></div>
                           <#list order.orderPositions as position>
                               <div class="row my-2 pr-2">
                                   <div class="col-auto">
                                       <img src="\upload\${position.productVersion.getMainImg()}" style="width: 50px"/>
                                   </div>
                                   <div class="col" style="line-height: 50px;">
                                       <a href="/products/${position.productVersion.id}" class="order-position-name">${position.productVersion.product.name}</a>
                                   </div>
                                   <div class="col-auto" style="line-height: 50px;">
                                       <span style="font-weight: 500">${position.productVersion.product.price}.-</span> x ${position.count}
                                   </div>
                               </div>
                           </#list>
                       </div>
                       <div class="col-3">
                           <span style="color: #999">Стоимость:</span><br/><span style="font-size: 25px">${order.cost} ₽</span><br/><br/>
                           <span style="color: #999">Статус:</span><br/><span style="font-size: 20px; font-weight: 500">${order.status.getName()}</span>
                       </div>
                   </div>
                   <div class="row justify-content-between mt-3 px-3">
                       <div class="get-check" id="get-check-${order.id}" onclick="getCheck(${order.id})">
                           Получить чек
                       </div>
                       <#if order.status.code == 0>
                           <div class="cancel-order" onclick="cancelOrderSure(this)">Отменить заказ</div>
                           <div class="cancel-order-sure row">
                               <div class="col-auto">Вы точно хотите отменить заказ?</div>
                               <div class="col-auto filter-on" onclick="cancelOrder(${order.id})">Да</div>
                               <div class="col-auto filter-on" onclick="cancelOrderCancel(this)">Нет</div>
                           </div>
                       </#if>
                   </div>
               </div>
           <#else>
               <p class="empty">У вас пока нет заказов</p>
           </#list>
       </div>
   </div>

   </@c.menu>
   <@c.script/>
</@c.page>

