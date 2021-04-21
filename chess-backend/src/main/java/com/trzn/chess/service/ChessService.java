package com.trzn.chess.service;

import com.trzn.chess.DTO.Move;
import com.trzn.chess.entity.Boards;

public interface ChessService {

	public Boolean createRoom(String userId);

	public Boards getBoard(String userId);
	
	public void chessMove(Move move);

	public int[] getBoards();

	public void gameOver(String userId);

	public Boolean joinRoom(Integer boardId, String userId);

}
