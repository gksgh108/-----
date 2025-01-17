<%--
  Created by IntelliJ IDEA.
  User: 82109
  Date: 2023-04-06
  Time: 오후 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>게시판 상세 페이지</title>
    <style type="text/css">
        div.container {
            width: 1000px;
        }
        p.title {
            border: 1px solid #000000;
            padding: 20px 10px;
            text-align: center;
            font-weight: bold;
            background: #f9f9f9;
        }
        table.form-table {
            width: 100%;
            border: 1px solid #000000;
            border-collapse: collapse;
            margin-bottom: 10px;
        }
        table.form-table th, table.form-table td {
            border: 1px solid #000000;
        }
        table.form-table th {
            width: 25%;
        }
        table.form-table td {
            padding: 2px 5px 3px 5px;
        }
        table.form-table td.long-text {
            white-space: pre-line;
        }
        div.button-area {
            text-align: right;
        }
        div.button-area a.button {
            font-size: 13px;
            border: 1px solid #000000;
            border-radius: 3px;
            padding: 3px 7px;
            text-decoration: none;
            color: #000000;
            background: #f9f9f9;
            cursor: pointer;
        }
        div.button-area a.button:hover {
            font-weight: bold;
            background: #e7e7e7;
        }
        div.check-wrap {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgb(0, 0, 0, .5);
            display: none;
        }
        div.check-wrap div.check-form {
            margin: 140px 20px 20px 20px;
            background: #FFFFFF;
            padding: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <p class="title">Spring boot로 만들어보는 게시판</p>
    <table class="form-table">
        <tr>
            <th>제목</th>
            <td>${board.title}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${board.writer}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td class="long-text">${board.contents}</td>
        </tr>
    </table>
    <div class="button-area">
        <a class="button left btn-action" href="#" data-action-type="modify">수정</a>
        <a class="button left btn-action" href="#" data-action-type="delete">삭제</a>
        <a class="button" href="/board/list">게시판 목록</a>
    </div>
</div>

<%-- 비밀번호 확인용 영역(팝업) --%>
<div id="check_wrap" class="check-wrap">
    <form id="action_form" method="post">
        <div class="check-form">
            <input type="hidden" id="board_seq" name="boardSeq" value="${board.boardSeq}" />
            <input type="hidden" id="action_type" class="initialized" value="" />

            <table class="form-table">
                <tr>
                    <th>비밀번호</th>
                    <td>
                        <input type="password" id="board_password" class="initialized"
                               placeholder="게시물 등록시 입력했던 비밀번호를 입력해주세요." style="width: 400px;"/>
                    </td>
                </tr>
            </table>
            <div class="button-area">
                <a class="button" id="btn_check_password">비밀번호 확인</a>
                <a class="button" id="btn_close">닫기</a>
            </div>
        </div>
    </form>
</div>

<!-- JQuery 3.X CDN 링크 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
       //수정/삭제 버튼 Click Event
       $('.btn-action').on('click', function() {
           //action type 설정
            $('#action_type').val($(this).data('action-type'));

            $('#check_wrap').show();
        });

        // 닫기 버튼 Click Event
        $('#btn_close').on('click', function() {
            //action type 설정
            $('#check_wrap input.initialized').val('');

            $('#check_wrap').hide();
        });

        // 비밀번호 확인 버튼 Click Event
        $('#btn_check_password').on('click', function() {
           $.ajax({ // 전체 페이지를 새로고침 안해도 뷰를 갱신
               url: '/board/checkPassword',
               type: 'POST',
               data: {
                   boardSeq : $('#board_seq').val(),
                   password : $('#board_password').val()
               },
               success: function (data) {
                   if (data == true) {
                       // 비밀번호 일치
                       var action_type = $('#action_type').val();
                       console.log('action_type : ' + action_type);

                       if (action_type == 'modify') {
                           // 수정폼으로 이동
                           $('#action_form').attr('action', '/board/modifyForm');
                           $('#action_form').submit();

                       } else if (action_type == 'delete') {
                           // 삭제 진행
                           $('#action_form').attr('action', '/board/delete');
                           $('#action_form').submit();
                       }

                   } else {
                       // 비밀번호 불일치
                       alert('비밀번호가 일치하지 않습니다.');
                   }
               },
               error: function(error){
                   console.log(error);
               }
           });
        });
    });
</script>
</body>
</html>