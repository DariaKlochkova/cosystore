<#import "parts/common.ftl" as c>
<@c.page>
   <@c.menu>

   <div id="content" class="container">
      <div id="carousel" class="carousel slide mb-5" data-ride="carousel">
         <ol class="carousel-indicators">
            <li data-target="#carousel" data-slide-to="0" class="active"></li>
            <li data-target="#carousel" data-slide-to="1"></li>
            <li data-target="#carousel" data-slide-to="2"></li>
         </ol>
         <div class="carousel-inner">
            <div class="carousel-item active" data-interval="8000">
               <a href="/products?category=10"><img src="img\Sofa.jpg" class="d-block w-100"></a>
            </div>
            <div class="carousel-item" data-interval="8000">
               <a href="/products?category=408"><img src="img\Carpet.jpg" class="d-block w-100"></a>
            </div>
            <div class="carousel-item" data-interval="8000">
               <a href="/products?category=16"><img src="img\Table.jpg" class="d-block w-100"><a>
            </div>
         </div>
      </div>
       <div id="popular">
           <#list popularProducts>
               <h3 style="text-align: center">Популярные товары</h3>
               <div class="wishes mb-5">
                   <#items as productVersion>
                       <div class="card">
                           <input class="pv-id" type="hidden" value="${productVersion.id}" />
                           <div class="card-img-holder">
                               <img class="card-img card-img-top" src="upload\${productVersion.getMainImg()}">
                           </div>
                           <div class="card-body">
                               <h5 class="card-title uppercase">${productVersion.product.name}</h5>
                               <p class="card-text" style="font-size: 13px">${productVersion.product.generalInf}</p>
                               <h4 class="price" style="font-weight: 500">${productVersion.product.price} ₽</h4>
                           </div>
                       </div>
                   </#items>
               </div>
           <#else>
           </#list>
       </div>
   </div>
   </@c.menu>
   <@c.script />
</@c.page>