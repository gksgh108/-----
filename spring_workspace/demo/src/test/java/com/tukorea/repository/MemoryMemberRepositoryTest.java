package com.tukorea.repository;

import java.util.*;

import com.tukorea.service.MemberService;
import org.junit.jupiter.api.*; //junit을 통해 생성하면 자동으로 생성

import com.tukorea.domain.*;

public class MemoryMemberRepositoryTest {
	MemoryMemberRepository repo = new MemoryMemberRepository();
	
	@Test
	public void testInsertMember() {
		// 회원 정보 설정
		Member member = new Member();
		member.setName("Spring");
		// 회원 정보 추가
		Member result = repo.insertMember(member);
		System.out.println("(1) result : " + result.getId() + " / " + result.getName());
	}
	
	@Test
	public void testSelectMemberById() {
		// 회원 정보 설정
		Member member = new Member();
		member.setName("Spring");
		// 회원 정보 추가
		repo.insertMember(member);
		// 아이디로 회원 정보 조회
		Member result = repo.selectMemberById(member.getId());
		// Member result = repo.selectMemberById(99); // 없을법한 임의의 숫자 넣어보기 - 에러!
		System.out.println("(2) result : " + result.getId() + " / " + result.getName());

	}
	
	@Test
	public void testSelectMemberByName() {
		// 회원 정보 설정
		Member member1 = new Member();
		member1.setName("hello1");
		Member member2 = new Member();
		member2.setName("hello2");
		// 회원 정보 추가
		repo.insertMember(member1);
		repo.insertMember(member2);
		// 이름으로 회원 정보 조회
		Member result = repo.selectMemberByName("hello1");
		// Member result = repo.selectMemberByName("Ji-Won"); // 없는 이름으로 조회해보기 - 에러!
		System.out.println("(3) result : " + result.getId() + " / " + result.getName());
	}
	
	@Test
	public void testSelectMemberList() {
		// 회원 정보 설정
		Member member1 = new Member();
		member1.setName("check-all 1");
		Member member2 = new Member();
		member2.setName("check-all 2");
		// 회원 정보 추가
		repo.insertMember(member1);
		repo.insertMember(member2);
		// 회원 목록 조회
		List<Member> result = repo.selectMemberList();
		System.out.println("(4) result : " + result.size());
	
	}
	
	// 객체 선언 타입을 MemberRepository에서 MemoryMemberRepository로 변경
	//MemoryMemberRepository repo = new MemoryMemberRepository();
	/**
	* 테스트 메서드 실행 후 매번 실행되는 메서드
	* store 객체 비움 진행
	*/
	@AfterEach
	public void afterEach() {
	repo.clearStorage();
	}


}
