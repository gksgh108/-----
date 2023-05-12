package com.tukorea.common.login.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface LoginDao {
    // 로그인 회원 정보 조회
    public HashMap<String, Object> selectMemberForLogin(HashMap<String, Object> paramMap);
    // 회원 최근 로그인 일시 정보 갱신
    public int updateMemberRecentLoginDatetime(HashMap<String, Object> paramMap);
}
