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

	var div = $(".color-menu");
	if (!div.is(e.target) && div.has(e.target).length === 0 && !$("#inputColor, #inputColorName").is(e.target)) {
		div.css("display", "none");
	}
});

$(".category-group-menu span").mouseover(function(e){
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

$(".category-menu a").click(function(e){ 
    $("#inputCategory").val(e.target.innerText);
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
    		document.getElementById('images').insertBefore(div, document.getElementById('file-input-div'));

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

//Редактор категорий
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

function inputProperty(category){
    if($("#" + category + " .add-property-list-btn").text() == "Добавить"){
        $("#" + category + " .input-property:last input, #" + category + " .input-property:last textarea").removeAttr("onkeyup");
        $('<div class="input-property row mb-2"><div class="col-4 pr-1"><input type="text" class="form-control" placeholder="Название" onkeyup="changePropertyInput(\'' + category + '\')"></div><div class="col-8 pl-1"><textarea class="form-control" placeholder="Возможные значения" onkeyup="changePropertyInput(\'' + category + '\')" style="height: 38px"></textarea></div></div>').insertBefore("#" + category + " .last-row");
        $("#" + category + " .input-property-small").css("display", "block");
        $("#" + category + " .add-property-list-btn").text("Отмена");
    }
    else {
        $("#" + category + " .input-property:last").remove();
        $("#" + category + " .input-property-small").css("display", "none");
        $("#" + category + " .add-property-list-btn").text("Добавить");
    }
};

var changePropertyInput = function(form){
    if($("#" + form + " .input-property:last input").val() != "" && $("#" + form + " .input-property:last textarea").val() != ""){
        $("#" + form + " .input-property:last input").attr("name", "propertyName");
        $("#" + form + " .input-property:last textarea").attr("name", "propertyValues");
        $("#" + form + " .add-property-list-btn").text("Добавить");
    }
    else if ($("#" + form + " .add-property-list-btn").text() == "Добавить") {
        $("#" + form + " .input-property:last input").removeAttr("name");
        $("#" + form + " .input-property:last textarea").removeAttr("name");
        $("#" + form + " .add-property-list-btn").text("Отмена");
    }
};

$(".editable").mouseover(function(e){
    $(this).find(".pen").css("display", "inline-block");
});

$(".editable").mouseout(function(e){
    $(this).find(".pen").css("display", "none");
});


