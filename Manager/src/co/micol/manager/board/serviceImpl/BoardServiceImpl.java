package co.micol.manager.board.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.manager.Dao;
import co.micol.manager.board.service.BoardService;
import co.micol.manager.board.vo.BoardVO;

public class BoardServiceImpl implements BoardService {
	private Connection conn = Dao.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<BoardVO> boardSelectList() {
		// TODO 전체목록조회
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo = null;
		String sql = "SELECT * FROM BOARD";
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardId(rs.getString("boardid"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setSubject(rs.getString("subject"));
				vo.setEnterDate(rs.getDate("enterdate"));
				vo.setHit(rs.getInt("hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public BoardVO boardSelect(BoardVO vo) {
		// TODO 게시글 상세조회
		String sql = "SELECT * FROM BOARD WHERE BOARDID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setSubject(rs.getString("subject"));
				vo.setEnterDate(rs.getDate("enterdate"));
				vo.setHit(rs.getInt("hit"));
				updateHit(vo.getBoardId());		// 조회수 증가 시키는 메소드 호출
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	private void updateHit(String boardId) {
		// TODO Auto-generated method stub
		String sql = "UPDATE BOARD SET HIT = HIT+1 WHERE BOARDID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardId);
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int boardInsert(BoardVO vo) {
		// TODO 게시글 추가
		String sql = "";
		return 0;
	}

	@Override
	public int boardUpdate(BoardVO vo) {
		// TODO 게시글 수정
		String sql = "";
		return 0;
	}

	@Override
	public int boardDelete(BoardVO vo) {
		// TODO 게시글 삭제
		String sql = "DELETE FROM BOARD WHERE BOARDID = ?";
		return 0;
	}


}
