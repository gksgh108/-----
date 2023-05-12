<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
    <div class="menu-wrap">
        <c:choose>
            <c:when test="${sessionScope.sMemberId ne null}">
                <a href="/logout" class="menu">로그아웃</a>
            </c:when>
            <c:otherwise>
                <a href="/login" class="menu">로그인</a>
                <a href="#" class="menu">회원가입</a>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="main-wrap">
        <div id="logo" style="display: inline-block;">
            <div class="dummy" style="width: 170px; padding: 15px 0;">LOGO</div>
        </div>
        <div class="dummy" style="float: right; display: inline-block; width: 170px; padding: 15px 0;">BANNER</div>
    </div>
</div>