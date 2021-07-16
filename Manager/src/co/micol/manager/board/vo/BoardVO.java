package co.micol.manager.board.vo;

import java.sql.Date;

public class BoardVO {
	private String boardId;
	private String writer;
	private String title;
	private String subject;
	private Date enterDate;
	private int hit;
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		System.out.println(" " + boardId + " : " + writer + " : " + title + " : " + enterDate + " : " + hit );
		return null;
	}
	
	
}
