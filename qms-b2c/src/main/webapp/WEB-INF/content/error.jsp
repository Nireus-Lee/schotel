<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
	<title>Q System</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	 <!-- Bootstrap -->
    <link href="assets/css/bootstrap.css" rel="stylesheet"  media="screen">
	<link href="assets/css/site.css" rel="stylesheet" media="screen">

    <!--[if lte IE 6]>
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap-ie6.css">
    <![endif]-->
    <!--[if lte IE 7]>
    <link rel="stylesheet" type="text/css" href="assets/css/ie.css">
    <![endif]-->

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	<script src="assets/js/libs/html5shiv.js"></script>
	<![endif]-->
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="alert alert-error">
				<h3>程序发生了意外，我们将尽快处理。</h3>	
				<br />
				<s:property value="exceptionStack"/>
				<a href="index.do">返回首页</a>	
			</div>
		</div>
	</div>
    
</body>
</html>
