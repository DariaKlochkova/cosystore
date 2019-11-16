<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/styles.css">
</head>
<body>
<#list orderPositions as orderPosition>
    <div class="row">
        <div class="col">
            ${orderPosition.productVersion.article}
        </div>
        <div class="col">
            <img src="upload\${orderPosition.productVersion.getMainImg()}" style="height: 40px">
        </div>
        <div class="col">
            ${orderPosition.productVersion.product.name}, ${orderPosition.productVersion.product.generalInf}
        </div>
        <div class="col">
            ${orderPosition.productVersion.product.price} руб
        </div>
        <div class="col">
            ${orderPosition.count}
        </div>
    </div>
</#list>
<div class="row">
    Итого: ${sum}
</div>
</body>