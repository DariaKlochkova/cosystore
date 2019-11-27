<#import "parts/common.ftl" as c>
<@c.page>
   <@c.menu>

   <div id="content" class="container">
      <div id="carousel" class="carousel slide" data-ride="carousel">
         <ol class="carousel-indicators">
            <li data-target="#carousel" data-slide-to="0" class="active"></li>
            <li data-target="#carousel" data-slide-to="1"></li>
            <li data-target="#carousel" data-slide-to="2"></li>
         </ol>
         <div class="carousel-inner">
            <div class="carousel-item active">
               <img src="img\Sofa.jpg" class="d-block w-100">
            </div>
            <div class="carousel-item">
               <img src="img\Carpet.jpg" class="d-block w-100">
            </div>
            <div class="carousel-item">
               <img src="img\Table.jpg" class="d-block w-100">
            </div>
         </div>
      </div>
   </div>
   </@c.menu>
   <@c.script />
</@c.page>