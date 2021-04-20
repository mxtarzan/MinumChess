package com.trzn.chess.service;

import com.trzn.chess.DTO.Move;
import com.trzn.chess.DTO.Piece;

public interface ChessService {

	public Boolean createRoom(String userId);

	public Piece[][] getBoard(String userId);
	
	public void chessMove(Move move);

	String getColor(String userId);

	public int[] getBoards();

	public Integer getTurn(String userId);
	
	public void gameOver(String userId);

	public Boolean joinRoom(Integer boardId, String userId);

}
