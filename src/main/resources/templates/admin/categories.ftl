<#import "../parts/common.ftl" as c>
<@c.page>
   <@c.admin "Редактирование категорий">

       <div class="row ml-0">
           <div class="col-2 p-0 mr-2 editable" style="min-width: fit-content">
               <h4>Группы <i class="fas fa-pencil-alt pen" onclick="openWindow('editGroup')"></i></h4>
               <div id="groupList">
                   <#list categoryGroups as categoryGroup>
                       <span class="dropdown-item">${categoryGroup.getName()}</span>
                   </#list>
               </div>
               <div id="addGroupListBtn" onclick="openWindow('addGroup')">Добавить</div>
           </div>
           <div class="col-2 p-0 mr-2 editable" style="min-width: fit-content">
               <h4>Категории  <i class="fas fa-pencil-alt pen" onclick="openWindow('editCategory')"></i></h4>
               <#list categoryGroups as categoryGroup>
                   <div class="categoryList" style="display: none">
                       <#list categoryGroup.getCategories() as category>
                           <a class="dropdown-item" href="#" id="${category.id}">${category.name}</a>
                       </#list>
                   </div>
               </#list>
               <div id="addCategoryListBtn" onclick="openWindow('addCategory')">Добавить</div>
           </div>
           <div class="col ml-3">
               <h4 class="editable">Характеристики товаров <span></span></h4>
               <#list categoryGroups as categoryGroup>
                   <#list categoryGroup.getCategories() as category>
                       <form action="/admin/categories/properties/${category.id}" method="post" id="category-${category.id}" style="display: none">
                           <input type="hidden" name="categoryName" value="${category.name}">
                           <div class="propertyList">
                               <#list category.getProperties() as property>
                                   <div class="row property-row pr-3">
                                       <div class="col-4 pr-0">
                                           <input type="text" name="propertyName" class="form-control" placeholder="Название" value="${property.name}">
                                       </div>
                                       <div class="col px-2">
                                           <textarea name="propertyValues" class="form-control" placeholder="Возможные значения" style="height: 38px">${property.posValToString()}</textarea>
                                       </div>
                                       <div class="col-auto delete-property">
                                           <i class="fas fa-trash-alt"></i>
                                       </div>
                                   </div>
                               </#list>
                               <div class="row last-row pr-3">
                                   <div class="col-4 pr-0">
                                       <div class="add-property-btn mt-0" onclick="addProperty('category-${category.id}')">Добавить</div>
                                   </div>
                                   <div class="col-8 pl-3">
                                       <small class="input-property-small form-text text-muted">Перечислите значения через запятую</small>
                                   </div>
                               </div>
                           </div>
                           <div class="row justify-content-end">
                               <div class="col-3">
                                   <div id="btn-save" onclick="saveProperties(${category.id})">Сохранить</div>
                               </div>
                           </div>
                       </form>
                   </#list>
               </#list>
           </div>
       </div>

   </@c.admin>

   <div id="fog">
       <div id="window">
           <form action="/admin/categories/group" method="post" id="deleteGroup">
               <input type="hidden" name="groupName">
               <input type="hidden" name="_method" value="DELETE">
               <input type="hidden" name="_csrf" value="${_csrf.token}"/>
           </form>
           <form action="/admin/categories/group" method="post" id="editGroup">
               <label for="inputNewName">Группа</label>
               <div class="row mb-4">
                   <div class="col-10 pr-0">
                       <input type="hidden" name="oldGroupName">
                       <input type="text" name="editGroupName" class="form-control">
                   </div>
                   <div class="col-2 pr-0 pl-1">
                       <div class="delete-icon" onclick="deleteItem('Group')"><i class="fas fa-trash-alt"></i></div>
                   </div>
               </div>
               <div class="row justify-content-between">
                   <div class="col-auto">
                       <div class="btn" id="win-cancel-btn" onclick="closeWindow()">Отмена</div>
                   </div>
                   <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                   <input type="hidden" name="_method" value="PUT">
                   <div class="col-auto">
                       <button type="submit" class="btn" id="win-save-btn">Сохранить</button>
                   </div>
               </div>
           </form>
           <form action="/admin/categories/group" method="post" id="addGroup">
               <label for="inputNewName">Новая группа</label>
               <div class="row mb-4">
                   <div class="col-12">
                       <input type="text" name="newGroupName" class="form-control">
                   </div>
               </div>
               <div class="row justify-content-between">
                   <div class="col-auto">
                       <div class="btn" id="win-cancel-btn" onclick="closeWindow()">Отмена</div>
                   </div>
                   <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                   <div class="col-auto">
                       <button type="submit" class="btn" id="win-save-btn">Добавить</button>
                   </div>
               </div>
           </form>

           <form action="/admin/categories" method="post" id="deleteCategory">
               <input type="hidden" name="categoryName">
               <input type="hidden" name="_method" value="DELETE">
               <input type="hidden" name="_csrf" value="${_csrf.token}"/>
           </form>
           <form action="/admin/categories" method="post" id="editCategory">
               <label for="inputNewName">Категория</label>
               <div class="row mb-4">
                   <div class="col-10 pr-0">
                       <input type="hidden" name="oldCategoryName">
                       <input type="text" name="editCategoryName" class="form-control">
                   </div>
                   <div class="col-2 pr-0 pl-1">
                       <div class="delete-icon" onclick="deleteItem('Category')"><i class="fas fa-trash-alt"></i></div>
                   </div>
               </div>
               <div class="row justify-content-between">
                   <div class="col-auto">
                       <div class="btn" id="win-cancel-btn" onclick="closeWindow()">Отмена</div>
                   </div>
                   <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                   <input type="hidden" name="_method" value="PUT">
                   <div class="col-auto">
                       <button type="submit" class="btn" id="win-save-btn">Сохранить</button>
                   </div>
               </div>
           </form>
           <form action="/admin/categories" method="post" id="addCategory">
               <label for="inputNewName">Новая категория</label>
               <div class="row mb-4">
                   <div class="col-12">
                       <input type="hidden" name="groupName">
                       <input type="text" name="newCategoryName" class="form-control">
                   </div>
               </div>
               <div class="row justify-content-between">
                   <div class="col-auto">
                       <div class="btn" id="win-cancel-btn" onclick="closeWindow()">Отмена</div>
                   </div>
                   <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                   <div class="col-auto">
                       <button type="submit" class="btn" id="win-save-btn">Добавить</button>
                   </div>
               </div>
           </form>
       </div>
   </div>
   <@c.script/>
</@c.page>