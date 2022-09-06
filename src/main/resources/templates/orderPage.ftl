<#import "parts/common.ftl" as c>
<@c.page>
   <@c.delivery "Обработка заказов">
       <div id="title">
           <h2>Заказ №${order.id}</h2>
           <hr>
       </div>
       <div id="order-page-content">
           <div id="order-positions">
               <div class="order-header" style="grid-column: 1/4;">
                   <span>Позиции</span>
               </div>
               <#list order.orderPositions as position>
                   <div>
                       <img src="\upload\${position.productVersion.getMainImg()}" style="width: 50px">
                   </div>
                   <div style="line-height: 50px;">
                       <a href="/products/${position.productVersion.id}">${position.productVersion.article}</a>
                   </div>
                   <div style="line-height: 50px;">${position.count}шт.</div>
               </#list>
           </div>
           <div id="recipient-contacts">
               <div class="order-header">
                   <span style="grid-column: 1/3;">Получатель</span>
               </div>
               <span>Имя</span><br>
               ${order.recipient}<br><br>
               <span>Телефон</span><br>
               ${order.phoneNumber}<br><br>
               <span>Email</span><br>
               ${order.email}<br><br>
               <span>Адрес доставки</span><br>
               ${order.address}<br><br>
           </div>
           <div id="order-progress">
               <div class="order-header">
                   <span style="grid-column: 1/3;">Выполнение заказа</span>
               </div>
               <span>Дата заказа</span><br>
               ${order.date}<br><br>
               <span>Статус заказа</span><br>
               ${order.status.getName()}<br><br>
               <div class="btn btn-lg" id="btn-add" style="margin-top: 85px" onclick="changeOrderStatus(${order.status.getCode()})">Принять заказ</div>
               <#if order.status.getCode() == 0>
                   <div class="deny-btn mt-3" onclick="denyOrderForm()">Отказаться</div>
               </#if>
           </div>
       </div>
   </@c.delivery>
    <div id="fog">
        <div id="window">
            <span style="font-size: large">Статус заказа изменён:</span><br>
            <span id="new-order-status" style="font-size: large; font-weight: bold">${order.status.getName()}</span>
               <div class="row justify-content-center" style="margin-top: 2.5rem">
                   <div class="btn ml-3" id="win-delete-btn" onclick="reload()">Ок</div>
               </div>
        </div>
        <div id="window-deny-order">
            <span style="font-size: large">Отказ от обработки заказа №${order.id}</span><br>
            <textarea class="form-control mt-2" id="deny-order-message" placeholder="Опишите причину отказа" style="height: 150px"></textarea>
            <div class="row justify-content-between mt-4 mb-2">
                <div class="col-auto"><div id="deny-cancel-btn" onclick="closeWindow()">Отмена</div></div>
                <div class="col-auto"><div class="lk-edit-btn" onclick="denyOrder(${order.id})">Отправить <i class="far fa-envelope"></i></div></div>
            </div>
        </div>
    </div>
    <@c.script>
        <script>
            switch (${order.status.getCode()}) {
                case 0 : $("#btn-add").text("Принять заказ");
                    break;
                case 1 : $("#btn-add").text("Заказ отправлен");
                    break;
                case 2 : $("#btn-add").text("Готов к выдаче");
                    break;
                default : $("#btn-add").remove();
            }
        </script>
    </@c.script>
</@c.page>
