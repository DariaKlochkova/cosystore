<#import "../parts/common.ftl" as c>
<@c.page>
   <@c.admin "Добавление товара">

      <form action="/admin/product" method="get" id="form-category">
         <div class="row mb-4">
            <div class="col-3">
               <label for="inputCategory">Категория</label>
               <input type="text" value="${selectedCategory.name}" id="inputCategory" class="form-control" onclick="showDropdown()" readonly>
               <input type="hidden" name="categoryId" id="categoryId" value="${selectedCategory.id}">
               <div class="dropdown-menu category-group-menu" id="admin-category-group-menu" aria-labelledby="inputCategory">
                  <#list categoryGroups as categoryGroup>
                     <span class="dropdown-item">${categoryGroup.getName()}</span>
                  </#list>
                  <#list categoryGroups as categoryGroup>
                     <div class="dropdown-menu category-menu" aria-labelledby="admin-category-group-menu">
                        <#list categoryGroup.getCategories() as category>
                           <span class="dropdown-item" id="${category.id}">${category.name}</span>
                        </#list>
                     </div>
                  </#list>
               </div>
            </div>
         </div>
      </form>

      <div id="productForm">
         <div class="row mb-4">
            <div class="col-2">
               <label for="inputArticle">Артикул</label>
               <input type="text" name="article" id="inputArticle" class="form-control" autocomplete="false">
            </div>
            <div class="col-3">
               <label for="inputName">Название</label>
               <input type="text" name="name" id="inputName" class="form-control" autocomplete="false">
            </div>
            <div class="col-3">
               <label for="inputName">Версия</label>
               <input type="text" name="versionName" id="inputVersionName" class="form-control" autocomplete="false">
            </div>
            <div class="col-2">
               <label for="inputPrice">Цена</label>
               <input type="number" name="price" id="inputPrice" class="form-control" min="0">
            </div>
            <div class="col-2">
               <label for="inputCount">Кол-во на складе</label>
               <input type="number" name="count" id="inputCount" class="form-control" min="0">
            </div>
         </div>
         <div class="row mb-4">
            <div class="col-5">
               <label for="inputInformation">Главная информация</label>
               <textarea id="inputInformation" name="information" class="form-control"></textarea>
               <small class="form-text text-muted">Отображается в карточке товара</small>
            </div>
            <div class="col-7">
               <label for="inputDescription">Описание</label>
               <textarea id="inputDescription" name="description" class="form-control"></textarea>
               <small class="form-text text-muted">Отображается на странице товара</small>
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
         <div class="row mb-5">
            <label for="inputHeight" class="col-sm-1 col-form-label">Высота</label>
            <div class="col-sm-3 pl-0">
               <input type="number" name="height" class="form-control" id="inputHeight" placeholder="см" min="0">
            </div>
            <label for="inputWidth" class="col-sm-1 col-form-label">Ширина</label>
            <div class="col-sm-3 pl-0">
               <input type="number" name="width" class="form-control" id="inputWidth" placeholder="см" min="0">
            </div>
            <label for="inputDepth" class="col-sm-1 col-form-label">Глубина</label>
            <div class="col-sm-3 pl-0">
               <input type="number" name="depth" class="form-control" id="inputDepth" placeholder="см" min="0">
            </div>
         </div>
         <div class="row">
            <div class="col-12">
               <label id="rooms">Комнаты</label>
            </div>
         </div>
         <div class="row mb-2">
            <div class="col-3">
               <div class="room-div" id="lounge">Гостиная</div>
            </div>
            <div class="col-3">
               <div class="room-div" id="bedroom">Спальня</div>
            </div>
            <div class="col-3">
               <div class="room-div" id="kitchen">Кухня</div>
            </div>
            <div class="col-3">
               <div class="room-div" id="bathroom">Ванная</div>
            </div>
         </div>
         <div class="row mb-5">
            <div class="col-3">
               <div class="room-div" id="cabinet">Кабинет</div>
            </div>
            <div class="col-3">
               <div class="room-div" id="nursery">Детская</div>
            </div>
            <div class="col-3">
               <div class="room-div" id="diningroom">Столовая</div>
            </div>
            <div class="col-3">
               <div class="room-div" id="hallway">Прихожая</div>
            </div>
         </div>

         <div class="property-list mb-4">
            <#list properties as property>
               <div class="row mb-4">
                  <div class="col-auto" style="text-align: right">
                     <label for="inputPropertyValue" class="col-form-label">${property.name}</label>
                  </div>
                  <div class="col">
                     <input type="hidden" value="${property.id}">
                     <input type="text" name="propertyValue" class="form-control input-property-value" onclick="showDropdown()" readonly>
                     <div class="dropdown-menu value-menu">
                        <#list property.possibleValues as value>
                           <span class="dropdown-item value-item">${value}</span>
                        </#list>
                     </div>
                  </div>
               </div>
            </#list>
         </div>

         <div class="row justify-content-end">
            <div class="btn btn-lg mr-3" id="btn-add" onclick="validateProduct('save')">Сохранить</div>
         </div>

      </div>

   </@c.admin>
   <@c.script />
</@c.page>