package com.tukorea.faq.dto;

public class FaqListDto {

    private String searchCategory; // 선택 카테고리
    private String searchType; // 검색유형
    private String searchKeyword; // 검색어
    private int pageNum; // 페이지 번호

    public FaqListDto() {
        // default 페이지 번호 설정
        this.pageNum = 1;
        // default 검색 조건 설정
        this.searchType = "question";
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
