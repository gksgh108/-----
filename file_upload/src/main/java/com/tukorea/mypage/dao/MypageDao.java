package com.tukorea.mypage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

@Mapper
public interface MypageDao {

    // 회원 정보 상세 조회
    public HashMap<String, Object> selectMemberinfo(int memberSeq);

    // 프로필 사진 수정
    public int updateProfileImage(HashMap<String, Object> paramMap);
}
