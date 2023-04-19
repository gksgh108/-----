<%--
  Created by IntelliJ IDEA.
  User: 82109
  Date: 2023-04-14
  Time: 오전 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta charset="UTF-8">
    <title>FAQ 목록</title>
<%--  FAQ 목록 CSS 파일 연결  --%>
<link type="text/css" rel="stylesheet" href="/css/faqList.css"/>

</head>
<body>
    <div class="container">
        <%--  페이지 제목 영역  --%>
        <div class="title">FAQ</div>

        <%--  검색 영역  --%>
        <<div class="search-wrap">
            <form id="faq_form" action="/" method="get">
                <input type="hidden" id="page_num" name="pageNum" value="${pageNum}" />
                <input type="hidden" id="category" name="searchCategory" value="${searchCategory}" />
                <select name="searchType">
                    <option value="question">제목</option>
                    <option value="answer">내용</option>
                </select>
                <input type="text" id="search_keyword" name="searchKeyword" value="${searchKeyword}"
                       placeholder="검색어를 입력해주세요." />
                <a class="button" id="btn_search" href="#">검색</a>
            </form>
        </div>

        <%--  목록 영역  --%>
        <div class="faq-list">
            <%--   카테고리 영역  --%>
            <div class="category">
                <div class="item <c:if test="${searchCategory eq null or searchCategory eq ''}">current</c:if>"
                     data-category="">전체</div>
                <c:forEach var="categoryStr" items="${categoryList}">
                    <div class="item <c:if test="${categoryStr eq searchCategory}">current</c:if>"
                         data-category="${categoryStr}">${categoryStr}</div>
                </c:forEach>

            </div>


            <%--   목록 영역  --%>
            <div class="list">
                <c:forEach var="faq" items="${faqList}">
                    <div class="item">
                        <div class="question">
                            <span>${faq.category}</span>${faq.question}
                        </div>
                        <div class="answer">${faq.answer}</div>
                    </div>
                </c:forEach>
            </div>

            <c:if test="${fn.length eq null or fn:length(faqList) eq 0}">
                <div class="no-contents">내용이 없습니다.</div>
            </c:if>
            <%--   페이징 영역  --%>
            <div class="paging">
                <c:choose>
                    <c:when test="${startUnitNum gt 1}">
                        <c:set var="beforePage" value="${startUnitNum-1}" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="beforePage" value="1" />
                    </c:otherwise>
                </c:choose>
                <a href="#" class="go-page" data-page-num="${beforePage}">&lt;</a>

                <c:forEach var="page" begin="${startUnitNum}" end="${endUnitNum}">
                    <a class="go-page<c:if test="${page eq pageNum}"> current</c:if>" href="#" data-page-num="${page}">${page}</a>
                </c:forEach>

                <c:choose>
                    <c:when test="${endUnitNum lt totalPagingNum}">
                        <c:set var="afterPage" value="${endUnitNum+1}" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="afterPage" value="${endUnitNum}" />
                    </c:otherwise>
                </c:choose>
                <a href="#" class="go-page" data-page-num="${afterPage}">&gt;</a>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
        });

        <%-- FAQ 질문 toggle 이벤트 --%>
        $('.faq-list .list .question').on('click', function() {
            if ($(this).hasClass('current')) {
                $(this).siblings('.answer').hide();
                $(this).removeClass('current');
            } else {
                // 초기화
                $('.faq-list .list .question.current').removeClass('current');
                $('.faq-list .list .answer:visible').hide();
                $(this).siblings('.answer').show();
                $(this).addClass('current');
            }
        });

        <%-- 엔터키 submit 막기 --%>
        $('#faq_form').on('keypress', function(e) {
            if (e.keyCode == 13) {
                e.preventDefault();
            }
        });

        <%-- 페이징 Click 이벤트 --%>
        $('.go-page').on('click', function() {
            $('#page_num').val($(this).data('page-num'));
            $('#faq_form').submit();
        });

        <%-- 카테고리 Click 이벤트 --%>
        $('.category .item').on('click', function() {
            $('#category').val($(this).data('category'));
            $('#page_num').val('1');
            $('#faq_form').submit();
        });

        <%-- 검색어 input KeyUp 이벤트 --%>
        $('#search_keyword').on('keyup', function(e) {
            e.preventDefault();
            if (e.keyCode == 13) {
                $('#btn_search').trigger('click');
            }
        });
        <%-- 검색 버튼 Click 이벤트 --%>
        $('#btn_search').on('click', function() {
            $('#page_num').val('1');
            $('#faq_form').submit();
        });
    </script>
</body>
</html>
