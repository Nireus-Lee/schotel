<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><s:text name="title.website" /></title>

    <link rel="stylesheet" href="/assets/css/bootstrap.css" />
    <link rel="stylesheet" href="/assets/css/jquery-ui-1.10.3.custom.css" />
    <link rel="stylesheet" href="/assets/css/buk.css" />
    <link rel="stylesheet" href="/assets/css/style.css" />

    <link rel="stylesheet" href="/assets/css/zy_common.css" />
    <link rel="stylesheet" href="/assets/css/date_input.css" />
    <link rel="stylesheet" href="/assets/css/jquery.suggest.css" />
    <link rel="stylesheet" href="/assets/css/aircraft_icons.css" />
</head>

<body>
<s:include value="../inc/top.htm" />
<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-2 col-md-10 col-xs-12">
            <table class="table table-hover">
                <tbody>
                    <s:iterator value="hotelInfos" status="stts">
                    <tr>
                        <td>
                                ${hotelName}
                        </td>
                        <td>
                                ${address}
                        </td>
                        <td>
                            <s:if test="hotelStarRate > 0.01">
                            酒店星级：${hotelStarRate}
                            </s:if>
                        </td>
                        <td>
                            <s:if test="hotelUserRate > 0.01">
                            用户评星：${hotelUserRate}
                            </s:if>
                        </td>
                    </tr>
                    <tr>
                        <table class="table">
                            <tbody>
                                <s:iterator value="ratePlans">
                                <tr>
                                    <td>${roomTypeName}</td>
                                    <td>${ratePlanCode}</td>
                                    <td>${price}</td>
                                </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                    </tr>
                </s:iterator>
                </tbody>
            </table>

        </div>
    </div>
</div>

<s:include value="../inc/bottom.jsp" />



</body>
</html>
