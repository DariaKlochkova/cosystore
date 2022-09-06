$('#inputArticle').inputmask('999.999.99');
$('#inputProductArticle').inputmask('999.999.99');
$('input [type="email"]').inputmask("email");
$('#email').inputmask("email");

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
    $('input[name="propertyValue"]').each(function () {
        if($(this).val() == ""){
            valid = false;
            alert("Введите значение свойства");
        }
    })

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
    var article = $("#inputArticle").val();
    var product = {
        id : $("#productId").val(),
        name : $("#inputName").val().toLowerCase(),
        versionName : $("#inputVersionName").val(),
        price : $("#inputPrice").val(),
        generalInf : $("#inputInformation").val(),
        description : $("#inputDescription").val(),
        height : $("#inputHeight").val(),
        width : $("#inputWidth").val(),
        depth : $("#inputDepth").val(),
        categoryId : $("#categoryId").val(),
        rooms : [],
        values : [],
        productVersions : [
            {
                id : $("#productVersionId").val(),
                article : article,
                count : $("#inputCount").val(),
                color : $("#inputColorName").val(),
                versionName : !!$("#inputVersionName").val() ? $("#inputVersionName").val() : '',
            }
        ]
    };
    $('input[name="room"]').each(function () {
        product.rooms.push($(this).val());
    });
    $('input[name="propertyValue"]').each(function () {
        product.values.push({
            id : { propertyId : $(this).prev().val() },
            value : $(this).val()
        });
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
                    if (act == 'edit'){
                        myAlert("Изменения сохранены");
                    } else {
                        if(response == "Товар добавлен в каталог")
                        myAlert(response, function () {
                            document.location.href = 'product/editor?article=' + article;
                        });
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
                    myAlert("Товар добавлен в каталог");
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
    if(!validateUserDataForm()) return;
    $("#order-btn").html('<div class="spinner-border" role="status"><span class="sr-only">Loading...</span></div>')

    var order = {
        cost : $("#sum-value").val(),
    };
    var formData = new FormData($('#productForm').get(0));
    formData.forEach(function(value, key){
        order[key] = value;
    });
    order.address = 'г. ' + order.city + ', ул. ' + order.street + ', д.' + order.house;
    if (order.apartment != '') order.address += ', кв. ' + order.apartment;
    order.recipient = order.surname + ' ' + order.firstname;
    if (order.patronymic != '') order.recipient += ' ' + order.patronymic;

    $.ajax({
        contentType: "application/json; charset=UTF-8",
        url: '/cart',
        data: JSON.stringify(order),
        dataType: "text",
        method: 'post',
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        success: function (response) {
            myAlert(response, function () {
                location.reload();
            });
        }
    });
}


function checkUserData(username, password) {
    if(validateUserDataForm()){
        var user = {};
        var formData = new FormData($('#lk-data-form').get(0));
        formData.forEach(function(value, key){
            if (value != '')
                user[key] = value;
        });

        $.ajax({
            contentType: "application/json; charset=UTF-8",
            url: '/lk',
            data: JSON.stringify(user),
            method: 'post',
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            success: function () {
                myAlert("Данные изменены", function() {
                    location.reload();
                });
            }
        });
    }
}

function validateUserDataForm(){
    var message = 'Введите';
    var valid = true;
    if($('#surname').val() == ""){
        $('#surname').css("border-color", "#d72828");
        valid = false;
        message += " фамилию,";
    } else $('#surname').removeAttr('style');
    if($('#firstname').val() == ""){
        $('#firstname').css("border-color", "#d72828");
        valid = false;
        message += " имя,";
    } else $('#firstname').removeAttr('style');
    if($('#city').val() == ""){
        $('#city').css("border-color", "#d72828");
        valid = false;
        message += " город,";
    } else $('#city').removeAttr('style');
    if($('#street').val() == ""){
        $('#street').css("border-color", "#d72828");
        valid = false;
        message += " улицу,";
    } else $('#street').removeAttr('style');
    if($('#house').val() == ""){
        $('#house').css("border-color", "#d72828");
        valid = false;
        message += " номер дома,";
    } else $('#house').removeAttr('style');
    if($('#email').val() == ""){
        $('#email').css("border-color", "#d72828");
        valid = false;
        message += " email,";
    } else $('#email').removeAttr('style');
    if($('#phoneNumber').val() == ""){
        $('#phoneNumber').css("border-color", "#d72828");
        valid = false;
        message += " номер телефона,";
    } else $('#phoneNumber').removeAttr('style');

    if(!valid)
        myAlert(message.substring(0, message.length - 1));
    return valid;
}

$('#password, #password-2').on('input', function() {
    if($('#password').val() != $('#password-2').val()){
        $('#password-2').css("border-color", "#d72828");
        $('#password-2-tip').fadeIn('fast');
        $('#register-btn').attr('disabled', 'disabled');
    } else {
        $('#password-2').removeAttr('style');
        $('#password-2-tip').fadeOut('fast');
        $('#register-btn').removeAttr('disabled');
    }
});
