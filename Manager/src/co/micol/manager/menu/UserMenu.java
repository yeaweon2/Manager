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

public class UserMenu {
	private MemberService memDao = new MemberServiceImpl();	// 멤버정보수정
	private BoardService boDao = new BoardServiceImpl();	// 게시글
	private Scanner scn = new Scanner(System.in);	
	
	private void title() {
		System.out.println("━━━━━━━━━━━━━━━【 사용자 메뉴 】━━━━━━━━━━━━━━━");
		System.out.println(" 1.개인정보수정 │ 2.조회 │ 3.작성 │ 4.수정 │ 5.종료" );
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
	}
	
	private void userMenu(MemberVO vo) {
		System.out.println();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" [ " + vo.getName() + " ] 님, 환영합니다.");
		boolean b = false;		
		while(!b) {
			title();
			switch(scanInt("메뉴선택 >> ")) {
				case 1 : userInfoUpdate(vo); break;
				case 2 : boardList(); break;
				case 3 : boardInsert(vo); break;
				case 4 : boardUpdate(vo); break;
				case 5 :
					b = true;
					System.out.println("프로그램을 종료합니다. ");
			}
		}
	}	
	
	private void userInfoUpdate(MemberVO vo) {
		// TODO 개인정보수정
		vo.setPassword(scanString("(수정) 패스워드 입력 >> "));
		vo.setName(scanString("(수정) 이름 입력 >> "));
		vo.setAddress(scanString("(수정) 주소 입력 >> "));
		vo.setTel(scanString("(수정) 연락처 입력 >> "));
		vo.setAge(scanInt("(수정) 나이 입력 >> "));
		int result = memDao.memberUpdate(vo);
		if(result > 0) {
			System.out.println("※ [" + vo.getName() + "] 님의 개인정보가 수정 되었습니다.");
		}else {
			System.out.println("※ 사용자 개인정보 수정 [ 실 패 ] ");
		}	
	}

	private void boardUpdate(MemberVO vo) {
		// TODO 게시글 수정
		BoardVO boVo = new BoardVO();
		String editNo = scanString("수정할 글ID 입력 >> ");
		boVo.setBoardId(editNo);
		boVo = boDao.boardSelect(boVo);
		
		if(boVo.getWriter().equals(vo.getId())) {
			boVo.setBoardId(editNo);
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
		
		BoardVO chkVo = boDao.boardDuplChk(boVo);
		if(chkVo == null) {
			System.out.println("※ 중복된 글ID가 존재합니다.");
		}else {
			boVo.setWriter(vo.getId());
			boVo.setTitle(scanString("제목 입력 >> "));
			boVo.setSubject(scanString("내용 입력 >> "));
			
			int result = boDao.boardInsert(boVo);
			if(result > 0) {
				System.out.println("※ [" + boVo.getTitle() + "] 추가되었습니다.");
			}else {
				System.out.println("※ 저장 [ 실 패 ] ");
			}
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
		userMenu(vo);
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
