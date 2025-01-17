package com.tukorea.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.tukorea.domain.Member;
import com.tukorea.repository.MemoryMemberRepository;

import org.junit.jupiter.api.*;

class MemberServiceTest {
	
	MemberService service;
	MemoryMemberRepository repo;
	
	@BeforeEach //초기화하고 시작
	void beforeEach() {
		repo = new MemoryMemberRepository();
		service = new MemberService(repo);
	}

	/**
	* 테스트 메서드 실행 후 매번 실행되는 메서드
	* store 객체 비움 진행
	*/
	@AfterEach
	public void afterEach() {
		repo.clearStorage();
	}

	@Test
	void testJoin() {
		// 회원 정보 설정
		Member member = new Member();
		member.setName("Spring");
		
		// 회원 정보 추가
		int memberId = service.join(member);
		System.out.println("(1) result : " + memberId);
	}

	@Test
	void testSameNameJoin() {
		// 회원 정보 설정
		Member member1 = new Member();
		member1.setName("Spring");
		
		Member member2 = new Member();
		member2.setName("Spring");

		// 회원 정보 추가
		service.join(member1);
		service.join(member2);

	}



	@Test
	void testGetMemberById() {
		fail("Not yet implemented");
	}

}
