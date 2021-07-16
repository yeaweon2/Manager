package co.micol.manager.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.micol.manager.board.service.BoardService;
import co.micol.manager.board.serviceImpl.BoardServiceImpl;
import co.micol.manager.member.service.MemberService;
import co.micol.manager.member.serviceImpl.MemberServiceImpl;
import co.micol.manager.member.vo.MemberVO;

public class AdminMenu {
	private MemberService memDao = new MemberServiceImpl();	// 멤버정보수정
	private BoardService boDao = new BoardServiceImpl();	// 게시글
	private Scanner scn = new Scanner(System.in);
	
	private void title() {
		System.out.println("━━━━━━━━━━━━━【 관리자 메뉴 】━━━━━━━━━━━━━");
		System.out.println(" 1.멤버관리  │  2.게시판관리  " );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");		
	}
	
	private void subMemberMenuTitle() {
		System.out.println("━━━━━━━━━━━━━━【 멤버관리 】━━━━━━━━━━━━━━");
		System.out.println(" 1.목록보기  │  2.멤버정보조회  │  3.멤버정보삭제" );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");		
	}	
	
	private void subBoardMenuTitle() {
		System.out.println("━━━━━━━━━━━━━【 게시판관리 】━━━━━━━━━━━━━");
		System.out.println(" 1.목록보기 │ 2.글쓰기 │ 3.글수정 │ 4.글삭제" );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");		
	}	
	
	private void adminMenu(MemberVO vo) {
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" [ " + vo.getName() + " ] 님, 환영합니다.");
		title();

		memberList();
		
	}
	
	private void memberList() {
		// TODO 멤버목록 가져오기
		List<MemberVO> list = new ArrayList<MemberVO>();
		list = memDao.memberSelectList();
		for(MemberVO vo : list) {
			vo.toString();
		}
	}

	public void run(MemberVO vo) {
		adminMenu(vo);
	}
}
