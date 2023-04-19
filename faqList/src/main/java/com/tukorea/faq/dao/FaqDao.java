package com.tukorea.faq.dao;

import com.tukorea.faq.domain.Faq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaqDao {
    // 카테고리 목록 조회
    public String[] selectFaqCategory();

    // FAQ 목록 총 건수 조회
    public int selectFaqListTotalCount(Map<String, Object> paramMap);

    // FAQ 목록 조회
    public List<Faq> selectFaqList(Map<String, Object> paramMap);


}
