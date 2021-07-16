package co.micol.manager.member.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.manager.Dao;
import co.micol.manager.member.service.MemberService;
import co.micol.manager.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	

	private Connection conn =  Dao.getConnection();	// DBMS와 연결하는 객체	
	private PreparedStatement psmt;					// conn을 통해서 sql 명령을 실행
	private ResultSet rs;							// select 구문을 호출시 결과를 돌려받는 객체

	@Override
	public List<MemberVO> memberSelectList() {
		// TODO 회원 전체 목록
		List<MemberVO> members = new ArrayList<>();
		MemberVO member = null;
		String sql = "SELECT * FROM MEMBER";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setTel(rs.getString("tel"));
				member.setAge(rs.getInt("age"));
				member.setAuthor(rs.getString("author"));
				members.add(member);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		// TODO 회원 조회	
		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setAge(rs.getInt("age"));
				vo.setAuthor(rs.getString("author"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return vo;		
	}

	@Override
	public MemberVO loginCheck(MemberVO vo) {
		// TODO 로그인 처리
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PASSWORD = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setAuthor(rs.getString("author"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// TODO 회원 추가
		String sql = "";
		return 0;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// TODO 회원 수정 
		return 0;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// TODO 회원 삭제
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		return 0;
	}

}
