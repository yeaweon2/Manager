package co.micol.manager;

import co.micol.manager.menu.LoginMenu;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		MemberService memberDao = new MemberServiceImpl();
//		List<MemberVO> list = new ArrayList<MemberVO>();
//		list = memberDao.memberSelectList();
//		for(MemberVO vo : list ) {
//			vo.toString();
//		}
		
//		MemberVO vo = new MemberVO();
//		vo.setId("hong");
//		vo = memberDao.memberSelect(vo);
//		vo.toString();
		
		LoginMenu menu = new LoginMenu();
		menu.login();
		
	}

}
