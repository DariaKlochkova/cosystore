<#import "parts/common.ftl" as c>
<@c.page>
   <@c.delivery "Обработка заказов">
       <#list orders>
       <div id="orders-table">
           <div class="order-row table-header">
               <div style="text-align: center">№</div>
               <div>Дата</div>
               <div>Адрес доставки</div>
               <div>Получатель</div>
               <div>Телефон</div>
               <div>Стоимость заказа</div>
               <div>Статус</div>
           </div>
           <#items as order>
               <div class="order-row" onclick="orderPage('${order.id}')">
                   <div style="text-align: center; color: #959ca3">
                       ${order.id}
                   </div>
                   <div>
                       ${order.date}
                   </div>
                   <div>
                       ${order.address}
                   </div>
                   <div>
                       ${order.recipient}
                   </div>
                   <div>
                       ${order.phoneNumber}
                   </div>
                   <div style="text-align: center">
                       ${order.cost} ₽
                   </div>
                   <div>
                       ${order.status.name()}
                   </div>
               </div>
           </#items>
       </div>
       <#else>
           <p class="empty">Список заказов пуст</p>
       </#list>

   </@c.delivery>
   <@c.script />
</@c.page>
