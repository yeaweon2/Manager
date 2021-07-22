package co.micol.manager.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.micol.manager.board.service.BoardService;
import co.micol.manager.board.serviceImpl.BoardServiceImpl;
import co.micol.manager.board.vo.BoardVO;
import co.micol.manager.member.service.MemberService;
import co.micol.manager.member.serviceImpl.MemberServiceImpl;
import co.micol.manager.member.vo.MemberVO;

public class AdminMenu {
	private MemberService memDao = new MemberServiceImpl();	// 멤버정보수정
	private BoardService boDao = new BoardServiceImpl();	// 게시글
	private Scanner scn = new Scanner(System.in);
	
	private void title() {
		System.out.println("━━━━━━━━━━━━━━━━【 관리자 메뉴 】━━━━━━━━━━━━━━━━");
		System.out.println(" 1.멤버관리  │  2.게시판관리  │  3.종료" );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}
	
	private void subMemberMenuTitle() {
		System.out.println("━━━━━━━━━━━━━━━━━【 멤버관리 】━━━━━━━━━━━━━");
		System.out.println(" 1.목록보기 │ 2.멤버추가 │ 3.멤버삭제 │ 4.종료" );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}	
	
	private void subBoardMenuTitle() {
		System.out.println("━━━━━━━━━━━━━━━━【 게시판관리 】━━━━━━━━━━━━━━━━");
		System.out.println(" 1.목록보기 │ 2.글쓰기 │ 3.글수정 │ 4.글삭제 │ 5.종료" );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");	
	}	
	
	private void adminMenu(MemberVO vo) {
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" [ " + vo.getName() + " ] 님, 환영합니다.");
		
		boolean b = false;
		while(!b) {
			title();
			switch(scanInt("메뉴선택 >> ")) {
				case 1 : memberSubMenu(vo); break;
				case 2 : boardSubMenu(vo); break;
				case 3 : b = true; System.out.println("프로그램 종료."); break;
			}
		}
		
	}
	
	private void boardSubMenu(MemberVO vo) {
		// TODO 2.게시판관리
		boolean b = false;
		while(!b) {
			subBoardMenuTitle();
			switch(scanInt("메뉴선택 >> ")) {
				case 1 : boardList(); break;
				case 2 : boardInsert(vo); break;
				case 3 : boardUpdate(vo); break;
				case 4 : boardDelete(); break;
				case 5 : b = true; break;
			}
		}
	}

	private void memberSubMenu(MemberVO vo) {
		// TODO 1.멤버관리
		boolean b = false;
		while(!b) {
			subMemberMenuTitle();
			switch(scanInt("메뉴선택 >> ")) {
				case 1 : memberList(); break;
				case 2 : memberInsert(vo); break;
				case 3 : memberDelete(vo); break;
				case 4 : b = true; break;
			}
		}		
	}
	
	private void memberInsert(MemberVO vo) {
		// TODO 멤버입력
		
		vo.setId(scanString("ID 입력 >> "));
		vo.setPassword(scanString("패스워드 입력 >> "));
		vo.setName(scanString("이름 입력 >> "));
		vo.setAddress(scanString("주소 입력 >> "));
		vo.setTel(scanString("연락처 입력 >> "));
		vo.setAge(scanInt("나이 입력 >> "));
		
		int result = memDao.memberInsert(vo);
		if(result > 0) {
			System.out.println("※ [" + vo.getName() + "] 입력 되었습니다.");
		}else {
			System.out.println("※ 멤버 입력 [ 실 패 ] ");
		}		
	}

	private void memberDelete(MemberVO vo) {
		// TODO 멤버삭제
		String adminId = vo.getId();
		vo.setId(scanString("삭제할 멤버 ID 입력 >> "));
		
		int result = memDao.memberDelete(vo);
		if(result > 0) {
			System.out.println("※ [" + vo.getId() + "] 삭제 되었습니다.");
		}else {
			System.out.println("※ 멤버 삭제 [ 실 패 ] ");
		}
		
	}

	private void boardUpdate(MemberVO vo) {
		// TODO 게시글 수정
		BoardVO boVo = new BoardVO();
		String editNo = scanString("수정할 글ID 입력 >> ");
		boVo.setBoardId(editNo);
		boVo = boDao.boardSelect(boVo);
		System.out.println(boVo.getWriter() + " / " + vo.getId());
		if(boVo.getWriter().equals(vo.getId())) {		
			boVo.setWriter(vo.getId());
			boVo.setTitle(scanString("제목 입력 >> "));
			boVo.setSubject(scanString("내용 입력 >> "));
			int result = boDao.boardUpdate(boVo);
			if(result > 0) {
				System.out.println("※ [" + boVo.getTitle() + "] 수정 되었습니다.");
			}else {
				System.out.println("※ 게시글 수정 [ 실 패 ] ");
			}		
		}else {
			System.out.println("※ 본인이 작성한 게시글만 수정이 가능합니다.");
		}
	}

	private void boardInsert(MemberVO vo) {
		// TODO 게시글 추가
		BoardVO boVo = new BoardVO();
		boVo.setBoardId(scanString("글ID 입력 >> "));
		boVo.setWriter(vo.getId());
		boVo.setTitle(scanString("제목 입력 >> "));
		boVo.setSubject(scanString("내용 입력 >> "));
		
		int result = boDao.boardInsert(boVo);
		if(result > 0) {
			System.out.println("※ [" + boVo.getTitle() + "] 추가 되었습니다.");
		}else {
			System.out.println("※ 게시글 저장 [ 실 패 ] ");
		}
	}	
	
	private void boardDelete() {
		// TODO (관리자권한) 게시글 삭제
		BoardVO boVo = new BoardVO();
		boVo.setBoardId(scanString("삭제할 게시글 ID 입력 >> "));
		
		int result = boDao.boardDelete(boVo);
		if(result > 0) {
			System.out.println("※ 해당 글이 삭제 되었습니다.");
		}else {
			System.out.println("※ 게시글 삭제 [ 실 패 ] ");
		}		
	}	

	private void memberList() {
		// TODO 멤버목록 가져오기
		List<MemberVO> list = new ArrayList<MemberVO>();
		list = memDao.memberSelectList();
		for(MemberVO vo : list) {
			vo.toString();
		}
	}

	private void boardList() {
		// TODO 게시판 목록 보기
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = boDao.boardSelectList();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		for(BoardVO vo : list) {
			vo.toString();
		}
	}
	
	public void run(MemberVO vo) {
		adminMenu(vo);
	}
	
	private String scanString(String msg) {
		System.out.print(msg);
		String val = scn.nextLine();
		return val;
	}
	
	private int scanInt(String msg) {
		System.out.print(msg);
		int val = 0;
		while(true) {
			try {
				val = scn.nextInt();
				scn.nextLine();
				break;
			}catch(Exception e) {
				System.out.print("다시 입력해주세요(숫자타입) >> ");
				scn.nextLine();
			}
		}
		return val;
	}	
}
