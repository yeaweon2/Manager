package co.micol.manager.board.service;

import java.util.List;

import co.micol.manager.board.vo.BoardVO;

public interface BoardService {
	
	// 게시글 목록 조회
	List<BoardVO> boardSelectList();
	
	// 게시글 상세조회
	BoardVO boardSelect(BoardVO vo);
	
	// 게시글 추가
	int boardInsert(BoardVO vo);
	
	// 게시글 수정
	int boardUpdate(BoardVO vo);
	
	// 게시글 삭제
	int boardDelete(BoardVO vo);
	
	BoardVO boardDuplChk(BoardVO vo);
	
}
