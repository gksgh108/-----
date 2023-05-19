<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>

<style type="text/css">
.container .contents table{
	width: 800px;
	border: 1px solid slategrey;
	border-collapse: collapse;
	margin-bottom: 15px;
}

.container .contents table th, .container .contents td{
	border: 1px solid slategrey;
	padding: 4px 8px 4px 8px

}

.contents .button-wrap {
	text-align: right;
}
.contents .button-wrap .button {
	cursor: pointer;
	padding: 5px 10px;
	background: #e0ebff;
	border: 1px solid #a3afc7;
}
</style>
</head>
<body>
<div class="container">
	<%-- 페이지 내용 영역 --%>
	<div class="contents">
		<table>
			<tr>
				<td rowspan="2">
					<img src="${member.PROFILE_IMG_SAVE_PATH}" />
				</td>
				<th>아이디</th>
				<td>${member.M_ID}</td>
			</tr>
			<tr>
				<th>프로필 이미지 첨부</th>
				<td>
					<input type="file" id="profile_img" />
				</td>
			</tr>
		</table>

		<div class="button-wrap">
			<span id="btn_modify" class="button">수정</span>
		</div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#btn_modify').on('click', function() {
			console.log($('#profile_img'));
			return false;

			var formData = new FormData();
			formData.append("profileImg", $('#profile_img')[0].files[0]);

			$.ajax({
				type:"post",
				enctype:'multipart/form-data',
				url:'/mypage/modifyProfileImage',
				data:formData,
				dataType:'json',
				processData : false,
				contentType : false,
				cache : false,
				success : function(data){
					console.log("success : ", data);
				},
				error : function(e){
					console.log("error : ", e);
				}
			});
		});
	});

</script>
</body>
</html>