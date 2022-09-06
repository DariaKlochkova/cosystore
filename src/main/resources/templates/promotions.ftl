<#import "parts/common.ftl" as c>
<@c.page>
   <@c.menu>

    <div id="content" class="container">
        <div id="title">
            <h2>Акции</h2>
        </div>

        <p class="empty mt-5">На данный момент нет действующих акций</p>
    </div>
    <div id="fog">
        <div id="wishes-login-window">
               <span style="font-size: large" id="window-message">
                   Войдите, чтобы добавлять товары в<br/><i class="far fa-heart"></i>&nbsp;Список желаний
               </span>
            <div class="row justify-content-between" style="position: relative; top: 30%">
                <div class="btn ml-3" id="win-delete-btn" onclick="closeDialog()">Отмена</div>
                <a href="/login">
                    <div class="btn mr-3 to-cart-btn">
                        <span id="ref-btn">Войти</span>
                    </div>
                </a>
            </div>
        </div>
    </div>
    </@c.menu>
    <@c.script/>
</@c.page>