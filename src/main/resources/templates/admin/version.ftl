<#import "../parts/common.ftl" as c>
<@c.page>
   <@c.admin "Добавление версии товара">

      <#list productVersions as productVersion>
         <div class="row mb-4">
            <div class="col-2">
               <label for="inputArticle">Артикул</label>
               <input type="text"class="form-control" value="${productVersion.article}" readonly>
            </div>
            <div class="col-5">
               <label for="inputVersionName">Название версии</label>
               <input type="text" class="form-control" value="${productVersion.versionName}">
            </div>
            <div class="col-2">
               <label for="inputCount">Кол-во на складе</label>
               <input type="number" class="form-control" value="${productVersion.count}" readonly>
            </div>
         </div>

         <div class="row">
            <div class="col-10">
               <label>Изображения</label>
            </div>
            <div class="col-2">
               <label for="checkColor">Цвет</label>
            </div>
         </div>
         <div class="row mb-5">
            <div class="col-10">
               <#list productVersion.images as image>
                  <div class="custom-file">
                     <img src="/upload/${image}" class="thumb">
                  </div>
               </#list>
            </div>
            <div class="col-2">
               <div style="height: 6rem">
                  <div class="form-control inputColor" style="background-color: ${productVersion.color.code}"></div>
                  <div class="row align-items-end mx-0" style="height: 50%;">
                     <input type="text" name="colorName" class="form-control" value="${productVersion.color.getName()}" readonly>
                  </div>
               </div>
            </div>
         </div>
         <hr class="bounder">
      </#list>

      <div class="row mb-4">
         <div class="col-2">
            <label for="inputArticle">Артикул</label>
            <input type="text" name="article" id="inputArticle" class="form-control">
         </div>
         <div class="col-5">
            <label for="inputVersionName">Название версии</label>
            <input type="text" name="versionName" id="inputVersionName" class="form-control">
         </div>
         <div class="col-2">
            <label for="inputCount">Кол-во на складе</label>
            <input type="number" name="count" id="inputCount" class="form-control" min="0">
         </div>
      </div>

      <div class="row">
         <div class="col-10">
            <label>Изображения</label>
         </div>
         <div class="col-2">
            <label for="checkColor">Цвет</label>
         </div>
      </div>
      <div class="row mb-4">
         <div class="col-10" id="images">
            <form method="post" enctype="multipart/form-data" id="imagesForm">
            <div class="custom-file" id="file-input-div">
               <input id="file-input" type="file" onchange="change(this)">
               <output id="list"></output>
               <label for="file-input" id="file-input-label">
                  <span id="img-icon"><i class="far fa-image"></i></span><br>Добавить фото
               </label>
            </div>
            <small class="form-text text-muted mt-0" style="width: 8rem; text-align: center;">Главное фото</small>
            </form>
         </div>
         <div class="col-2">
            <div style="height: 6rem">
               <div id="inputColor" class="form-control" onclick="showColorDropdown()"></div>
               <div class="row align-items-end mx-0" style="height: 50%;">
                  <input type="text" name="colorName" id="inputColorName" class="form-control" value="Чёрный" onclick="showColorDropdown()" readonly>
                  <div class="color-menu" style="display: none">
                     <#list colors as color>
                     <div class="color-menu-item">
                        <#if color.getCode() == "">
                           <div class="color-menu-item-box" style="background-image: url('/img/multicolor.png')"></div>
                        <#else>
                           <div class="color-menu-item-box" style="background-color: ${color.getCode()}"></div>
                        </#if>
                        <div class="color-menu-item-name">${color.getName()}</div>
                     </div>
                     </#list>
                  </div>
               </div>
            </div>
         </div>
      </div>


      <input type="hidden" name="_csrf" value="${_csrf.token}" />
      <div class="row justify-content-end">
         <div class="btn btn-lg mr-3" id="btn-add" onclick="validateProductVersion()">Сохранить версию</div>
      </div>

      </@c.admin>
   <@c.script />
</@c.page>