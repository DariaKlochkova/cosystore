$("#product-menu-btn").click(function (e) {
    $("#product-menu").css("display", "grid");
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

    div = $("#product-menu-btn");
    if (!div.is(e.target) && div.has(e.target).length === 0) {
        $("#product-menu").css("display", "none");
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
    $("input[name='groupName']").val($(".selected-group").text());
    $("input[name='categoryName']").val($(".selected-category").text());
    $("#delete" + item).submit();
};

function closeWindow(){
    $("#window").css("display", "none");
    $("#fog").css("display", "none");
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
        }
    });
};



// Корзина

function productToCart(userId, productId){
    var cart = {
        userId : userId,
        productId : productId,
        _csrf : $('meta[name="csrf-token"]').attr('content')
    };
    $.post('/products', cart, function(data) {
        alert(data);
    });
};

function deleteProductFromCart(productVersionId){
    var cart = {
        productVersionId : productVersionId
    };

    $.ajax({
        url: '/cart',
        data: cart,
        dataType: "text",
        method: 'delete',
        headers: {
            'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
        },
        success: function(response){
            location.reload();
        }
    });
};

function changeProductCount(productVersionId, input) {
    if (Number.parseInt(input.value) > Number.parseInt(input.max)){
        alert('К сожалению, у нас есть только ' + input.max + ' единиц данного товара');
        input.value = input.max;
    } else if (Number.parseInt(input.value) < 1){
        input.value = 1;
    }
    var cart = {
        productVersionId : productVersionId,
        count : input.value
    };
    $.ajax({
        url: '',
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
}









