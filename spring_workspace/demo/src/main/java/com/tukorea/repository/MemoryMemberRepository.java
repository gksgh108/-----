package com.tukorea.repository;

import java.util.*;

import com.tukorea.domain.Member;

public class MemoryMemberRepository implements MemberRepositoryIF{ // 다중상속 = implements => Override를 해야하므로 자동으로 설정

	private static Map<Integer, Member> store = new HashMap<>(); // store 테이블에 member를 integer형태로 저장 python 딕셔너리
	private static int sequence = 0;
	
	/**
	* 회원 정보 등록 구현 메서드
	*/
	@Override
	public Member insertMember(Member member) {
		// member id 설정
		member.setId(++sequence);
		// store 객체에 member 정보 추가
		store.put(member.getId(), member);
		return member;

	}
	
	/**
	* 아이디로 회원 정보 조회 구현 메서드
	*/
	@Override
	public Member selectMemberById(int id) {
		// id key 값을 이용하여 store 객체에서 정보 꺼내기
		return store.get(id);
		
	}
	
	@Override
	public Member selectMemberByName(String name) {
		Member result = null;
		Iterator<Integer> keys = store.keySet().iterator(); // set은 순서가 없으므로 iterator써서 순환을 해줘야한다.
		while(keys.hasNext()) { // boolean 형태로 반환
			Member tMember = store.get(keys.next());
			if (name.equals(tMember.getName())) {
				result = tMember;
				break;
		}
	}
	return result;

		
	}
	
	@Override
	public List<Member> selectMemberList() {
		// store 객체를 List 형태로 변환하여 return
		return new ArrayList<>(store.values());
	}
	
	/**
	* store 객체 비움 구현 메서드
	*/
	public void clearStorage() {
	// sequence 초기화
	sequence = 0;
	// store 객체 비우기
	store.clear();
	}

}
