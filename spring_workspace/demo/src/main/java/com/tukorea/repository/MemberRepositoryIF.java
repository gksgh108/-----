package com.tukorea.repository;

import java.util.*;

import com.tukorea.domain.Member;

public interface MemberRepositoryIF { //다중 상속
	//회원 등록
	Member insertMember(Member member);
	
	Member selectMemberById(int id);

	Member selectMemberByName(String name);

	List<Member> selectMemberList();
}
