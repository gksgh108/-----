<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>

<jsp:include page="/WEB-INF/common/template/script.jsp"></jsp:include>

</head>
<body>

<div class="container">
	<%-- 헤더 영역 --%>
	<jsp:include page="/WEB-INF/common/template/header.jsp"></jsp:include>

	<%-- 페이지 내용 영역 --%>
	<div class="contents">
		<div class="dummy" style="margin-bottom: 10px; padding: 50px 0;">BANNER</div>
		<div class="dummy" style="padding: 200px 0;">CONTENTS</div>
	</div>
</div>

</body>
</html>