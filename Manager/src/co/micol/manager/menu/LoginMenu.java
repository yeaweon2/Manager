package co.micol.manager.menu;

import java.util.Scanner;

import co.micol.manager.member.service.MemberService;
import co.micol.manager.member.serviceImpl.MemberServiceImpl;
import co.micol.manager.member.vo.MemberVO;

public class LoginMenu {
	private MemberService memberDao = new MemberServiceImpl();
	private Scanner scn = new Scanner(System.in);
	
	// 사용자 메뉴 객체 생성
	private UserMenu user = new UserMenu();
	// 관리자 메뉴 객체 생성
	private AdminMenu admin = new AdminMenu();
	
	private void loginTitle() {
		System.out.println();
		System.out.println("━━━━━━━━━━━━━【 로 그 인 】━━━━━━━━━━━━━");
	}

	private MemberVO loginCheck() {
		MemberVO vo = new MemberVO();
		boolean b = false;
		int loginCount = 1;
		do {
			loginTitle();
			System.out.print("ID입력 >> ");
			vo.setId(scn.nextLine());
			System.out.print("Password 입력 >> ");
			vo.setPassword(scn.nextLine());
			
			vo = memberDao.loginCheck(vo);
			if(vo.getName() != null) {
				b = true;
			} else {
				System.out.println("※ 존재하지 않는 ID 또는 Password가 틀렸습니다.");
				if(loginCount == 3) {
					b = true;
					System.out.println("※ 로그인시도 " + loginCount + "회 초과 관리자에게 문의하세요.");
					vo = new MemberVO();
				} else {
					loginCount++;
				}
			}
		} while (!b);
		return vo;
	}

	public void login() {
		MemberVO vo = new MemberVO();
		vo = loginCheck();
		if(vo.getId() != null) {
			if(vo.getAuthor().equals("ADMIN")) {
				admin.run(vo);
			}else{
				user.run(vo);
			} 
		}
	}
}
