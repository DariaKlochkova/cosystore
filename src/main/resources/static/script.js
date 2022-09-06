function myAlert(message, func){
    var alert = $('<div id="my-alert-wrapper"><div id="my-alert">' +
                     '<div id="my-alert-message">' + message + '</div>' +
                     '<div id="my-alert-btn">Ок</div>' +
                  '</div></div>');
    $('body').append(alert);
    $('#my-alert-btn').click(function () {
        alert.remove();
        if(func === undefined) return;
        func();
    })
}

function myInf(message){
    var alert = $('<div id="my-inf-wrapper"><div id="my-inf">' + message + '</div>');
    $('body').append(alert);
    var f = function(){alert.remove();};
    setTimeout(f, 2000);
}



$("#product-menu-btn").click(function (e) {
    $("#product-menu").css("display", "grid");
    $(this).css("background-color", "white");
    $(this).css("box-shadow", "0px 0px 4px rgba(0, 0, 0, 0.3)");
});

$("#room-menu-btn").click(function (e) {
    $("#room-menu").css("display", "grid");
    $(this).css("background-color", "white");
    $(this).css("box-shadow", "0px 0px 4px rgba(0, 0, 0, 0.3)");
});

$("#product-menu-groups span").mouseover(function(e){
    $(".product-menu-categories").each(function(){
        $(this).css("display", "none");
    })
    $("#product-menu-groups span").each(function(){
        $(this).css("background-color", "white");
    })
    $(this).css("background-color", "#e7d03a");
    var div = $(".product-menu-categories").eq(($("#product-menu-groups span").index($(this))));
    div.css("display", "block");
});
$("#product-menu-groups span:first").mouseover();

$(".product-menu-categories-item").click(function (e) {
    document.location.href = this.href;
})

$("#edit").click(function (e) {
    $("#edit-window").css("display", "block");
    $("#edit-fog").css("display", "block");
    $("#edit").css("background-color", "#e7d03a");
    $("#edit").css("color", "#000");
})

function openProductEditor() {
    $("#article-form").attr("action", "/admin/product/editor");
    $("#article-form").submit();
}

function openVersionsEditor(productId){
    document.location.href = productId;
}


// Добавление товара

function showDropdown(){
    $(".category-group-menu").css("display", "block");
};

function showColorDropdown(){
	$(".color-menu").css("display", "grid");
    $(".color-filter-menu").css("display", "block");
};

$(document).click(function hideDropdown(e){ 
    var div = $(".category-group-menu");
    if (!div.is(e.target) && div.has(e.target).length === 0 && !$("#inputCategory").is(e.target)) {
        div.css("display", "none");
    }

	div = $(".color-menu");
	if (!div.is(e.target) && div.has(e.target).length === 0 && !$("#inputColor, #inputColorName").is(e.target)) {
		div.css("display", "none");
	}

    div = $(".filter-menu");
    if (!div.is(e.target) && div.has(e.target).length === 0 && !$("#inputPriceFilter").is(e.target) && !$("#inputColorName").is(e.target)) {
        div.css("display", "none");
    }

    div = $("#product-menu-btn");
    if (!div.is(e.target) && div.has(e.target).length === 0) {
        $("#product-menu").css("display", "none");
        div.css("background-color", "inherit");
        div.css("box-shadow", "none");
    }

    div = $("#room-menu-btn");
    if (!div.is(e.target) && div.has(e.target).length === 0) {
        $("#room-menu").css("display", "none");
        div.css("background-color", "inherit");
        div.css("box-shadow", "none");
    }

    div = $("#edit-window");
    if (!div.is(e.target) && div.has(e.target).length === 0 && !$("#edit").is(e.target)) {
        div.css("display", "none");
        $("#edit-fog").css("display", "none");
        $("#edit").removeAttr("style");
    }
});

$(".category-group-menu > span").mouseover(function(e){
	$(".category-group-menu .category-menu").each(function(){
		$(this).css("display", "none");
	})
	$(".category-group-menu span").each(function(){
		$(this).css("background-color", "white");
	})
	$(this).css("background-color", "#3ab4b4");
	var div = $(".category-group-menu .category-menu").eq(($(".category-group-menu span").index($(this))));
    div.css("display", "block");
}); 

$(".category-menu span").click(function(e){
    $("#inputCategory").val(e.target.innerText);
    $("#categoryId").val(e.target.id);
    $(".category-group-menu").css("display", "none");
    $("#form-category").submit();
});


var change = function(evt) {
    var f = evt.files[0]; // FileList object
    // Only process image files.
    if (!f.type.match('image.*')) {
      return;
    }

    var reader = new FileReader();
    // Closure to capture the file information.
    reader.onload = (function(file) {
    	return function(e) {
    		// Render thumbnail.
    		var div = document.createElement('div');
    		div.className='custom-file';
    		div.innerHTML = ['<img class="thumb" src="', e.target.result, '" title="', file.name, '"/>'].join('');
    		document.getElementById('imagesForm').insertBefore(div, document.getElementById('file-input-div'));
    		//$("#imagesForm").append()

    		var input = $("#file-input");
    		input.removeAttr("id");
    		input.attr("name","files");
    		$('#file-input-div').prev().append($('#file-input-div>input'));

    		input = document.createElement('input'); 
    		input.setAttribute("id","file-input");
    		input.setAttribute("type","file");
    		input.setAttribute("onchange","change(this)");
    		document.getElementById('file-input-div').insertBefore(input, document.getElementById('list'));
    	};
    })(f);
    // Read in the image file as a data URL.
    reader.readAsDataURL(f);
};


$(".room-div").click(function (e) {
	var i = $(this).attr("id");
	if($(this).css("color") == "rgb(0, 0, 0)"){
	    $(this).css("border","1px solid #ced4da");
	    $(this).css("color","#b3b8be");
	    $(['input[name="room"][value="',i , '"]'].join('')).remove();
	} 
	else{
	    $(this).css("border","2px solid #2eb8b3");
	    $(this).css("color","black");
	    $(['<input type="hidden" name="room" value="',i , '">'].join('')).insertBefore($("#rooms"));
	}
});

$(".color-menu-item").click(function(e){
    $("#inputColor").css("background-color", $(this).find('.color-menu-item-box').css("background-color"));
    $("#inputColor").css("background-image", $(this).find('.color-menu-item-box').css("background-image"));
	$("#inputColorName").val($(this).find('.color-menu-item-name').text());
	$(".color-menu").css("display", "none");
});

function rgb_to_hex(color){
	var rgb = color.replace(/\s/g,'').match(/^rgba?\((\d+),(\d+),(\d+)/i);
	return (rgb && rgb.length === 4) ? "#" + ("0" + parseInt(rgb[1],10).toString(16)).slice(-2) + ("0" + parseInt(rgb[2],10).toString(16)).slice(-2) + ("0" + parseInt(rgb[3],10).toString(16)).slice(-2) : color;
}

$(".input-property-value").click(function (e) {
    $(this).next().css("display", "block");
})

$(".value-item").click(function (e) {
    $(this).parent().prev().val($(this).text());
})

$(document).click(function (e) {
    var div = $(".input-property-value");
    if (!div.is(e.target) && div.has(e.target).length === 0) {
        $(".value-menu").css("display", "none");
    }
})


// Редактор категорий

$("#groupList span").click(function(e){
	$(".categoryList").each(function(){
		$(this).css("display", "none");
	})
	$("#groupList span").each(function(){
        $(this).removeClass("selected-group");
	})
    $(this).addClass("selected-group");
	var div = $(".categoryList").eq(($("#groupList span").index($(this))));
	div.css("display", "block");
});
$("#groupList span:first").click();


$(".categoryList a").click(function(e){
	$(".categoryList a").each(function(){
		$(this).removeClass("selected-category");
	})
	$(this).addClass("selected-category");
    $("h4 > span").text($(this).text());
    $("input[name='categoryName']").val($(this).text());
	$("[id|='category']").each(function(){
		$(this).css("display", "none");
	})
    $("#category-" + $(this).attr("id")).css("display", "block");
});
$(".categoryList:first a:first").click();

function openWindow(form){
    $("#window").css("display", "block");
    $("#fog").css("display", "block");
    $("#window > form").each(function(){
        $(this).css("display", "none");
    })
    $("#"+form).css("display", "block");
    $("#"+form+" > div > div > input[type='text']").focus();
    $("input[name='oldGroupName']").val($(".selected-group").text());
    $("input[name='editGroupName']").val($(".selected-group").text());
    $("input[name='groupName']").val($(".selected-group").text());
    $("input[name='oldCategoryName']").val($(".selected-category").text());
    $("input[name='editCategoryName']").val($(".selected-category").text());
};

function deleteItem(item){

    if(item == 'Group'){
        var data = {
            groupName : $(".selected-group").text()
        };
        $.ajax({
            url: '/admin/categories/group',
            data: data,
            method: 'delete',
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success: function (response) {
                $(".selected-group").remove();
                $("#groupList span:first").click();
                closeWindow();
                myAlert(response);
            }
        });
    }
    else if(item == 'Category'){
        var data = {
            categoryName : $(".selected-category").text()
        };
        $.ajax({
            url: '/admin/categories',
            data: data,
            method: 'delete',
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success: function (response) {
                var div = $(".selected-category").parent().find("a:first");
                $(".selected-category").remove();
                div.click();
                closeWindow();
                myAlert(response);
            }
        });
    }
};

function closeWindow(){
    $("#window").fadeOut('fast');
    $("#window-deny-order").fadeOut('fast');
    $("#fog").fadeOut('fast');
};

function addProperty(category){
    $("#" + category + " .property-row:last input, #" + category + " .property-row:last textarea").removeAttr("onkeyup");
    $('<div class="row property-row pr-3"><div class="col-4 pr-0"><input type="text" name="propertyName" class="form-control" placeholder="Название" onkeyup="changePropertyInput(\'' + category + '\')"></div><div class="col px-2"><textarea class="form-control" name="propertyValues" placeholder="Возможные значения" onkeyup="changePropertyInput(\'' + category + '\')" style="height: 38px"></textarea></div><div class="col-auto delete-property" id="temp"><i class="fas fa-trash-alt"></i></div></div>').insertBefore("#" + category + " .last-row");
    $("#temp").click(deleteProperty);
    $("#temp").removeAttr("id");
    $("#" + category + " .input-property-small").css("display", "block");
    var btn = $("#" + category + " .add-property-btn");
    btn.toggleClass("add-property-btn add-property-btn-disabled");
    btn.removeAttr("onclick");
};

var deleteProperty = function(e){
    if($(this).parent().find("input, textarea").css("color") == "rgb(0, 0, 0)"){
        if($(this).parent().find("input, textarea").val() == ""){
            var form = $(this).closest("form").attr("id");
            $(this).parent().remove();
            changePropertyInput(form);
        }
        else {
            $(this).parent().find("input, textarea").css("color", "#ced4da");
            $(this).html("<i class=\"fas fa-undo-alt\"></i>");
            $(this).css("font-size","19px");
            $(this).parent().find("input, textarea").attr("disabled", "true");
            $(this).parent().find("input, textarea").removeAttr("name");
        }
    }
    else{
        $(this).parent().find("input, textarea").css("color", "black");
        $(this).html("<i class=\"fas fa-trash-alt\"></i>");
        $(this).css("font-size","22px");
        $(this).parent().find("input, textarea").removeAttr("disabled");
        $(this).parent().find("input").attr("name", "propertyName");
        $(this).parent().find("textarea").attr("name", "propertyValues");
    }
};
$(".delete-property").click(deleteProperty);

var changePropertyInput = function(form){
    var propertyInput = $("#" + form + " .property-row:last input");
    var propertyTextarea = $("#" + form + " .property-row:last textarea");
    if(propertyInput.val() != "" || propertyTextarea.val() != "" || propertyInput.length == 0){
        var btn = $("#" + form + " .add-property-btn-disabled");
        btn.toggleClass("add-property-btn add-property-btn-disabled");
        btn.attr("onclick", "addProperty('" + form + "')");
    }
    else {
        var btn = $("#" + form + " .add-property-btn");
        btn.toggleClass("add-property-btn add-property-btn-disabled");
        btn.removeAttr("onclick");
    }
};

$(".editable").mouseover(function(e){
    $(this).find(".pen").css("display", "inline-block");
});

$(".editable").mouseout(function(e){
    $(this).find(".pen").css("display", "none");
});


function saveProperties(categoryId){
    var properties = [];
    $("#category-" + categoryId + " .property-row").has("[name]").each(function(){
        properties.push({
            name : $(this).find("input[name=propertyName]").val(),
            possibleValues : $(this).find("textarea[name=propertyValues]").val().split(', ')
        })
    });

    var category = {
        id : categoryId,
        properties : properties
    };
    var json = JSON.stringify(category);

    $.ajax({
        contentType: "application/json; charset=UTF-8",
        url: '/admin/categories/properties',
        data: json,
        method: 'put',
        headers: {
            'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
        },
        success: function (response) {
            myAlert(response);
        }
    });
};



// Список желаний

function productToWishes(productId){
    if($('#userName').length == 0){
        $("#fog").fadeIn('fast');
        $("#wishes-login-window").fadeIn('fast');
        return;
    }
    $('a[href="/wishes"]').css("animation", "wish 1s cubic-bezier(0.65, 0.05, 0.36, 1)");
    var data = {
        _csrf : $('meta[name="csrf-token"]').attr('content')
    };
    $.post('/wishes/' + productId, data);

    var f = function(){$('a[href="/wishes"]').css("animation", "none")};
    setTimeout(f, 1000);

    myInf('Продукт добавлен в список желаний');
};

$(".wish-btn").click(function () {
    if($('#userName').length == 0){
        $("#fog").fadeIn('fast');
        $("#wishes-login-window").fadeIn('fast');
        return;
    }
    var icons = $(this).find(".h-icon").children();
    if(icons.eq(0).css("display") == "inline-block"){
        productToWishes($(this).attr('id'));
        icons.eq(0).css("display", "none");
        icons.eq(1).css("display", "none");
    } else {
        deleteProductFromWishes($(this).attr('id'));
        icons.eq(0).css("display", "inline-block");
        icons.eq(1).css("display", "inline-block");
    }
})

function deleteProductFromWishes(productId){
    $.ajax({
        url: '/wishes/' + productId,
        dataType: "text",
        method: 'delete',
        headers: {
            'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
        }
    });
};

$(".wish-btn-mini").click(function () {
    var id = $(this).attr('id');
    deleteProductFromWishes(id);
    $(".card").has("input.pv-id[value='" + id + "']").remove();
})



// Корзина

function productToCart(productId){
    var data = {
        _csrf : $('meta[name="csrf-token"]').attr('content')
    };
    $.post('/cart/' + productId, data, function(response) {
        $("#window-message").text(response);
        $("#ref-btn").text("В корзину");
        openDialog();
    });
};

function deleteProductFromCart(productVersionId){
    $.ajax({
        url: '/cart/' + productVersionId,
        dataType: "text",
        method: 'delete',
        headers: {
            'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
        },
        success: function(){
            $(".cart-position").has("input.pv-id[value='" + productVersionId + "']").remove();
            getCartSum();
        }
    });
};

function changeProductCount(productVersionId, input) {
    if (Number.parseInt(input.value) > Number.parseInt(input.max)){
        myAlert('К сожалению, у нас есть только ' + input.max + ' единиц данного товара');
        input.value = input.max;
    } else if (Number.parseInt(input.value) < 1){
        input.value = 1;
    }
    var cart = {
        count : input.value
    };
    $.ajax({
        url: '/cart/' + productVersionId,
        data: cart,
        method: 'put',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

    getCartSum();
}

function getCartSum(){
    var sum = 0;
    $(".cart-position").each(function() {
        var reg = new RegExp(String.fromCharCode(160), "g");
        sum += (Number.parseInt($(this).find($(".price-value")).val().replace(reg,'')) * $(this).find($('input[name="count"]')).val());
    })
    $("#sum-value").val(sum);
    sum = (sum+'').replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1"+String.fromCharCode(160));
    $("#cart-sum-value").text(sum);
}

function openOrderForm() {
    $("#order-form").css("display", "block");
    $("#order-btn").text("Заказать");
    $("#order-btn").attr("style", "background-color: #e7d03a; border: 1px solid white; color: black;");
    $("#order-btn").attr("onclick", "sendOrder()");
    $(".delete-cart-position").removeAttr("onclick");
    $(".delete-cart-position").css("border-color", "#ddd");
    $(".delete-cart-position").css("color", "#ddd");
    $(".delete-cart-position").css("cursor", "default");
}


// Прочее

function deleteSpaces() {
    var reg = new RegExp(String.fromCharCode(160), "g");
    $("input[type='number']").each(function () {
        $(this).val($(this).attr("value").replace(reg,''));
    })
}


function openDialog() {
    $("#fog").fadeIn('fast');
    $("#window").fadeIn('fast');
}
function closeDialog() {
    $("#fog").fadeOut('fast');
    $("#window").fadeOut('fast');
    $("#wishes-login-window").fadeOut('fast');
}
function deleteProduct() {
    $.ajax({
        url: '',
        data: {productVersionId : $("#productVersionId").val()},
        method: 'delete',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        success: function (response) {
            $(".admin-panel").html("<p class='empty'>" + response + "</p>");
            closeDialog();
        }
    });
}

function changeOrderStatus(statusCode) {
    $.ajax({
        url: '',
        data: {newStatus : ++statusCode},
        method: 'put',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        success: function(response){
            $("#new-order-status").text(response);
            openDialog();
        }
    });
}

function reload() {
    location.reload();
}

function orderPage(id) {
    document.location.href = '/orders/' + id;
}


// Каталог товаров

var openProductPage = function (e) {
    var div = $(".product-btns");
    if (!div.is(e.target) && div.has(e.target).length === 0){
        document.location.href = '/products/' + $(this).find(".pv-id").val();
    }
}
$(".card").click(openProductPage);


// Страница товара

$('#gallery-carousel').on('slide.bs.carousel', function (e) {
    $(".img-mini").eq(e.from).css("box-shadow", "none");
    $(".img-mini").eq(e.to).css("box-shadow", "0 0 0 3px rgba(0,0,0,0.3)");
})

$(".img-mini").click(function () {
    $('.carousel').carousel($(".img-mini").index($(this)));
})

function productVersionMenu() {
    if($("#product-version-menu").css("display") == 'none'){
        $("#product-version-menu").css("display", "grid");
        $("#product-version .down-btn img").css("transform", "rotate(180deg)");
    } else {
        $("#product-version-menu").css("display", "none");
        $("#product-version .down-btn img").removeAttr('style');
    }
}

$(".product-version-menu-li").click(function () {
    document.location.href = this.id;
})


function logout() {
    $.ajax({
        url: '/logout',
        method: 'post',
        headers: {
            'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
        },
        success: function(){
            document.location.href = '/';
        }
    });
}

function logoutDialog() {
    $("#logout-window").fadeIn('fast');
    $("#logout-fog").fadeIn('fast');
}
function closeLogoutDialog() {
    $("#logout-window").fadeOut('fast');
    $("#logout-fog").fadeOut('fast');
}


// Личный кабинет

function openLkEdit() {
    $("#lk-data").css("display", "none");
    $("#lk-data-edit").css("display", "block");
}

function closeLkEdit() {
    $("#lk-data").css("display", "block");
    $("#lk-data-edit").css("display", "none");
}

function cancelOrderSure(div){
    div.style = "display: none";
    div.nextElementSibling.style = "display: flex";
}
function cancelOrderCancel(div){
    div.parentElement.style = "display: none";
    div.parentElement.previousElementSibling.style = "display: block";
}


// Фильтры

$('.color-menu-item').click(function () {
    $(this).toggleClass('on');
    $('.filter-on').addClass('able');
})

function sendFilters() {
    $(document).click();
    $('#minPrice, #maxPrice').change();
    var data = {
        category : getUrlVar().category,
        minPrice : $('#minPrice').val(),
        maxPrice : $('#maxPrice').val()
    };
    var colors = '';
    $(".color-menu-item.on").each(function(){
        colors += $(this).find('.color-menu-item-name').text() + ',';
    });
    if(colors != '')
        data.colors = colors.slice(0,-1);
    $.get('/products', data, function(products) {
        $(".goods").html($('.goods', products).html());
        $(".card").click(openProductPage);
        filterIcons();
    })
    $('.filter-on').removeClass('able');
}



function propertyFilters() {
    var values = [];

    $('input[name="propertyValue"]').each(function () {
        if($(this).val() != ''){
            values.push({
                propertyId : $(this).prev().val(),
                value : $(this).val()
            });
        }
    });

    $.ajax({
        contentType: "application/json; charset=UTF-8",
        url: '/products',
        data: JSON.stringify(values),
        dataType: "text",
        method: 'post',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        success: function(products) {
            $(".goods").html($('.goods', products).html());
            $(".card").click(openProductPage);
        }
    })
}

function filterIcons(){
    if($(".color-menu-item.on").length > 0){
        $('.fa-angle-down:first').css("display", "none");
        $('.fa-times:first').css("display", "inline-block");
    } else {
        $('.fa-angle-down:first').css("display", "inline-block");
        $('.fa-times:first').css("display", "none");
    }
    if($('#minPrice').val() > 0 && $('#maxPrice') != 100000){
        $('.fa-angle-down:last').css("display", "none");
        $('.fa-times:last').css("display", "inline-block");
    } else {
        $('.fa-angle-down:last').css("display", "inline-block");
        $('.fa-times:last').css("display", "none");
    }
}

$('#minPrice, #maxPrice').change(function () {
    if(Number.parseInt($('#minPrice').val()) > Number.parseInt($('#maxPrice').val())){
        var temp = $('#minPrice').val();
        $('#minPrice').val($('#maxPrice').val());
        $('#maxPrice').val(temp);
    }
    range(Number.parseInt($('#minPrice').val()), Number.parseInt($('#maxPrice').val()));
    $('.filter-on').addClass('able');
})

function range(from, to) {
    $( "#slider-range" ).slider({
        range: true,
        min: 0,
        max: 100000,
        values: [ from, to ],
        slide: function( event, ui ) {
            $('#minPrice').val(ui.values[0]);
            $('#maxPrice').val(ui.values[1]);
            if($('#minPrice').val() > 0 || $('#maxPrice') != 100000){
                $('.filter-on').addClass('able');
            }
        }
    });
}
range(0, 100000)

$(document).keypress(function(e) {
    if(e.which == 13) sendFilters();
});

function getUrlVar(){
    let urlVar = window.location.search;
    let arrayVar = (urlVar.substr(1)).split('&');
    let valueAndKey = [];
    let resultArray = {};
    if(arrayVar[0]=="") return false;
    for (let i = 0; i < arrayVar.length; i ++) {
        valueAndKey = arrayVar[i].split('=');
        resultArray[valueAndKey[0]] = valueAndKey[1];
    }
    return resultArray;
}

function showPriceDropdown(){
    $(".price-filter-menu").css("display", "block");
};

function resetColor() {
    $(".color-menu-item.on").each(function(){
        $(this).removeClass("on");
    });
    sendFilters();
}
function resetPrice() {
    $('#minPrice').val(0);
    $('#maxPrice').val(100000);
    sendFilters();
}


// Личный кабинет

function cancelOrder(orderId) {
    $.ajax({
        url: '/lk/order',
        data: {orderId: orderId},
        method: 'post',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        success: function(){
            location.reload();
        }
    });
}

function getCheck(orderId) {
    $("#get-check-" + orderId).html('<div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div>')
    $.ajax({
        url: '/lk/order',
        data: {orderId: orderId},
        method: 'get',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        success: function(response){
            myAlert(response);
            $("#get-check-" + orderId).html('Получить чек');
        }
    });
}

function denyOrderForm() {
    $("#window-deny-order").css("display", "block");
    $("#fog").css("display", "block");
}

function denyOrder(orderId) {
    $.ajax({
        url: '/orders/'+orderId,
        data: {
            message: $('#deny-order-message').val()
        },
        method: 'post',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        success: function(response){
            myAlert(response);
        }
    });
}









