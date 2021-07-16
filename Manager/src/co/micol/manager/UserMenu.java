package co.micol.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.micol.manager.board.service.BoardService;
import co.micol.manager.board.serviceImpl.BoardServiceImpl;
import co.micol.manager.board.vo.BoardVO;
import co.micol.manager.member.service.MemberService;
import co.micol.manager.member.serviceImpl.MemberServiceImpl;
import co.micol.manager.member.vo.MemberVO;

public class UserMenu {
	private MemberService memDao = new MemberServiceImpl();	// 멤버정보수정
	private BoardService boDao = new BoardServiceImpl();	// 게시글
	private Scanner scn = new Scanner(System.in);	
	
	private void title() {
		System.out.println("━━━━━━━━━━━━━【 사용자 메뉴 】━━━━━━━━━━━━━");
		System.out.println(" 1.개인정보수정 │ 2.조회 │ 3.작성 │ 4.수정" );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
	}
	
	private void userMenu(MemberVO vo) {
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" [ " + vo.getName() + " ] 님, 환영합니다.");
		title();
		boardList();
	}	
	
	private void boardList() {
		// TODO 게시판 목록 보기
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = boDao.boardSelectList();
		for(BoardVO vo : list) {
			vo.toString();
		}
	}

	public void run(MemberVO vo) {
		userMenu(vo);
	}
}
