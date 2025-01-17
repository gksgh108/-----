package com.tukorea.service;

import java.util.*;

import com.tukorea.domain.Member;
import com.tukorea.repository.*;

public class MemberService {
	
	private final MemberRepositoryIF repo;
	
	public MemberService(MemberRepositoryIF repo) { //생성자 주입 - 원래는 new로 선언을 해야하지만 spring의
		this.repo = repo;							//특성인 DI로 인해 외부에서 설정을 통해 정의
	}

	
	/**
	* 회원가입 서비스 메서드 구현
	*/
	public int join(Member member) {
		// 같은 이름의 회원은 가입 불가
		Member cMember = repo.selectMemberByName(member.getName());
		if(cMember != null) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
		// 회원 가입 진행
		repo.insertMember(member);
		
		return member.getId();
	}
	
	/**
	* 회원 목록 조회 서비스 메서드 구현
	*/
	public List<Member> getMemberList() {
	return repo.selectMemberList();
	}
	
	/**
	* 아이디로 회원 정보 조회 서비스 메서드 구현
	*/
	public Member getMemberById(int id) {
	Member result = repo.selectMemberById(id);
	return result;
	}
	
}
