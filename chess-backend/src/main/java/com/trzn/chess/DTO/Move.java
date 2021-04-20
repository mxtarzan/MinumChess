package com.trzn.chess.DTO;

public class Move {
	private String userId;
	private Integer yourRow;
	private Integer yourCol;
	private Integer thierRow;
	private Integer thierCol;
	
	public Integer getYourRow() {
		return yourRow;
	}
	public void setYourRow(Integer yourRow) {
		this.yourRow = yourRow;
	}
	public Integer getYourCol() {
		return yourCol;
	}
	public void setYourCol(Integer yourCol) {
		this.yourCol = yourCol;
	}
	public Integer getThierRow() {
		return thierRow;
	}
	public void setThierRow(Integer thierRow) {
		this.thierRow = thierRow;
	}
	public Integer getThierCol() {
		return thierCol;
	}
	public void setThierCol(Integer thierCol) {
		this.thierCol = thierCol;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
