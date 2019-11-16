$('#inputArticle').inputmask('999.999.99');
$('#inputProductArticle').inputmask('999.999.99');

function validateProduct(act){
    var valid = true;
    if($('#inputArticle').val() == ""){
        valid = false;
        alert("Введите артикул");
    }
    if($('#inputName').val() == ""){
        valid = false;
        alert("Введите название товара");
    }
    if(!(Number.parseInt($('#inputPrice').val()) > 0)){
        valid = false;
        alert("Введите корректную цену товара");
    }
    if(!(Number.parseInt($('#inputCount').val()) >= 0)){
        valid = false;
        alert("Введите неотрицательное число");
    }
    if($('input[name="files"]').length == 0 && $('input[name="images"]').length == 0){
        valid = false;
        alert("Загрузите хотя бы одно изображение");
    }
    if($('input[name="room"]').length == 0){
        valid = false;
        alert("Выберите хотя бы одну комнату");
    }

    if(valid){
        $.ajax(saveProductRequest(act));
    }
}

function validateProductVersion(act){
    var valid = true;
    if($('#inputArticle').val() == ""){
        valid = false;
        alert("Введите артикул");
    }
    if(!(Number.parseInt($('#inputCount').val()) >= 0)){
        valid = false;
        alert("Введите неотрицательное число");
    }
    if($('input[name="files"]').length == 0){
        valid = false;
        alert("Загрузите хотя бы одно изображение");
    }

    if(valid){
        saveProductVersion();
    }
}

function saveProductRequest(act){
    var product = {
        id : $("#productId").val(),
        name : $("#inputName").val(),
        price : $("#inputPrice").val(),
        generalInf : $("#inputInformation").val(),
        description : $("#inputDescription").val(),
        height : $("#inputHeight").val(),
        width : $("#inputWidth").val(),
        depth : $("#inputDepth").val(),
        categoryId : $("#categoryId").val(),
        rooms : [],
        productVersions : [
            {
                id : $("#productVersionId").val(),
                article : $("#inputArticle").val(),
                count : $("#inputCount").val(),
                color : $("#inputColorName").val(),
                versionName : !!$("#inputVersionName").val() ? $("#inputVersionName").val() : '',
            }
        ]
    };
    $('input[name="room"]').each(function () {
        product.rooms.push($(this).val());
    });

    var imgForm = new FormData($('#imagesForm').get(0));

    var uploadImages = {
        processData: false,
        contentType: false,
        data: imgForm,
        url: '/admin/product/img',
        dataType: "json",
        method: 'post',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        success: function(response){
            var imgArray = [];
            $('input[name="images"]').each(function () {
                imgArray.push($(this).val());
            })
            Array.prototype.push.apply(imgArray, response);
            product.productVersions[0].images = imgArray;
            $.ajax({
                contentType: "application/json; charset=UTF-8",
                url: '',
                data: JSON.stringify(product),
                dataType: "text",
                method: 'post',
                headers: {
                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                },
                success: function(response){
                    if(act == 'add'){
                        document.location.href = 'product/' + response;
                    } else if (act == 'edit'){
                        alert("Изменения сохранены");
                    } else {
                        alert("Товар добавлен в каталог");
                    }
                }
            });
        }
    }

    return uploadImages;
}

function saveProductVersion(){
    var imgForm = new FormData($('#imagesForm').get(0));

    var uploadImages = {
        processData: false,
        contentType: false,
        data: imgForm,
        url: '/admin/product/img',
        dataType: "json",
        method: 'post',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        success: function(response){
            productVersion.images = response;
            $.ajax({
                contentType: "application/json; charset=UTF-8",
                url: '',
                data: JSON.stringify(productVersion),
                dataType: "text",
                method: 'post',
                headers: {
                    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                },
                success: function(response){
                    alert("Товар добавлен в каталог");
                    document.location.href = response;
                }
            });
        }
    }

    var productVersion = {
        article : $("#inputArticle").val(),
        count : $("#inputCount").val(),
        color : $("#inputColorName").val(),
        versionName : $("#inputVersionName").val()
    };

    $.ajax(uploadImages);
}

function sendOrder(){
    alert("Заказ принят. Чек отправлен вам на почту");
    var orderPositions = [];
    $(".cart-position").each(function () {
        orderPositions.push({
            id : { productVersion : $(this).find(".pv-id").val() },
            count : $(this).find(".input-count").val()
        })
    });

    var order = {
        cost : $("#sum-value").val(),
        address : $("#inputAddress").val(),
        email : $("#inputEmail").val(),
        recipient : $("#inputRecipient").val(),
        phoneNumber : $("#inputPhone").val(),
        orderPositions : orderPositions
    };

    $.ajax({
        contentType: "application/json; charset=UTF-8",
        url: '/cart',
        data: JSON.stringify(order),
        dataType: "text",
        method: 'post',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        // success: function(response){
        //     alert(response);
        // }
    });
}




