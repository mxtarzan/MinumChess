package com.trzn.chess.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="boards")
public class Boards {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardId;
	private String fenNotation;
	private String whiteId = null;
	private String blackId= null;
	private Integer turn = 0;

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public String getfenNotation() {
		return fenNotation;
	}

	public void setfenNotation(String fenNotation) {
		this.fenNotation = fenNotation;
	}

	public String getWhiteId() {
		return whiteId;
	}

	public void setWhiteId(String whiteId) {
		this.whiteId = whiteId;
	}

	public String getBlackId() {
		return blackId;
	}

	public void setBlackId(String blackId) {
		this.blackId = blackId;
	}

	public Integer getTurn() {
		return turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}
}
