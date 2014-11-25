<%@ page language="java" contentType="text/html; charset=gb2312" pageEncoding="gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="gb2312">
    <link rel="stylesheet" href="/assets/css/bootstrap.css" />
    <link rel="stylesheet" href="/assets/css/jquery-ui-1.10.3.custom.css" />
</head>
<body>
<form id="frmPayment" name="frmPayment" action="${payUrl}" method="post">
    <input type="hidden" name="Description" value="${orderDesc}">
    <input type="hidden" name="PaymentDescription" value="${paymentDesc}">
    <input type="hidden" name="OrderID" value="${openApiTempOrderId}" >
    <input type="hidden" name="OrderType" value="1">
    <input type="hidden" name="ReturnUrl" value="${returnUrl}" >
    <input type="hidden" name="ShowUrl" value="${showUrl}?id=${orderId}" >
    <input type="hidden" name="OrderSummary" value="OrderSummary" >
    <input type="submit" value="去支付页面" name="Submit">
</form>

<!- -[if lt IE 9]>
<script src="/assets/js/libs/jquery-1.11.0.min.js"></script>
<script src="/assets/js/libs/jquery-migrate-1.2.1.min.js"></script>
<![endif]- ->
<!--[if gte IE 9]><!-->
<script src="/assets/js/libs/jquery-2.1.0.min.js"></script>
<!--<![endif]-->


<script>
    $(function(){
        $("#frmPayment").submit();
    })
</script>

</body>
</html>
