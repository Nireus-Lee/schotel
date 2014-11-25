<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/assets/css/bootstrap.css" />
    <link rel="stylesheet" href="/assets/css/jquery-ui-1.10.3.custom.css" />
</head>
<body>
    <s:property value="errorMsg" /><br />
    <s:property value="errorCode" /><br />
    <s:property value="transactionID" /><br />
    <s:property value="amount" /><br />
    <s:property value="currencyCode" /><br />
    <s:property value="orderID" /><br />
    <s:property value="status" /><br />
    <s:if test="errorCode == null">
        支付成功！
    </s:if>
</body>
</html>
