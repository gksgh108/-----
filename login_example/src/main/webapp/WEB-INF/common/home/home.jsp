<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>

<link type="text/css" rel="stylesheet" href="/web/css/common.css" />

</head>
<body>

<div class="container">
	<%-- 헤더 영역 --%>
	<div class="header">
		<div class="menu-wrap">
			<a href="#" class="menu">로그인</a>
			<a href="#" class="menu preparing">회원가입</a>
		</div>
		<div class="main-wrap">
			<div id="logo" style="display: inline-block;">
				<div class="dummy" style="width: 170px; padding: 15px 0;">LOGO</div>
			</div>
			<div class="dummy" style="float: right; display: inline-block; width: 170px; padding: 15px 0;">BANNER</div>
		</div>
	</div>

	<%-- 페이지 내용 영역 --%>
	<div class="contents">
		<div class="dummy" style="margin-bottom: 10px; padding: 50px 0;">BANNER</div>
		<div class="dummy" style="padding: 200px 0;">CONTENTS</div>
	</div>
</div>

</body>
</html>