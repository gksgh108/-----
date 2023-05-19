<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>

<style type="text/css">
.contents .button_wrap {
	width: 800px;
	margin: 100px auto;
	text-align: center;
}

.contents .button{
	display: inline-block;
	background: skyblue;
	border: 1px solid slategrey;
	padding: 100px 90px;
}

.contents .button_wrap a{
	text-decoration: none;
	color: #000;
}

</style>
</head>
<body>
<div class="container">
	<%-- 페이지 내용 영역 --%>
	<div class="contents">
		<div class="button_wrap">
			<c:choose>
				<c:when test="${sessionScope.sMemberSeq ne null}">
					<a href="/mypage">
						<div class="button">마이페이지</div>
					</a>
					<a href="#">
						<div class="button">게시판</div>
					</a>
					<a href="/logout">
						<div class="button">로그아웃</div>
					</a>
				</c:when>
				<c:otherwise>
					<a href="/login">
						<div class="button">로그인</div>
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

</body>
</html>