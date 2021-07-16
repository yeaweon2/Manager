package co.micol.manager.member.service;

import java.util.List;

import co.micol.manager.member.vo.MemberVO;

public interface MemberService {
	
	// 전체 회원 목록조회
	List<MemberVO> memberSelectList();
	
	// 회원조회
	MemberVO memberSelect(MemberVO vo);
	
	// 로그인 처리
	MemberVO loginCheck(MemberVO vo);
	
	// 회원 입력
	int memberInsert(MemberVO vo);
	
	// 회원 수정
	int memberUpdate(MemberVO vo);
	
	// 회원 삭제
	int memberDelete(MemberVO vo);
	
}
