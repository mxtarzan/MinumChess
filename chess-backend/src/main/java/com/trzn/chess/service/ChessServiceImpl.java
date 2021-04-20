package com.trzn.chess.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Square;
import com.trzn.chess.DTO.Move;
import com.trzn.chess.DTO.Piece;
import com.trzn.chess.entity.Boards;
import com.trzn.chess.repository.ChessRepository;

@Service(value = "engineService")
@Transactional
public class ChessServiceImpl implements ChessService {

	@Autowired
	ChessRepository repo;

	@Override
	public Boolean createRoom(String userId) {
		System.out.println(userId);
		Boards board = new Boards();
		board.setWhiteId(userId);
		board.setfenNotation(
				"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		repo.save(board);
		return true;
	}

	public String getColor(String userId) {
		Boards board = repo.findByWhiteId(userId);
		if (board != null) {
			return "White";
		}
		board = repo.findByBlackId(userId);
		if (board != null) {
			return "Black";
		}
		return "N/A";
	}

	@Override
	public Piece[][] getBoard(String userId) {
		Piece[][] board = new Piece[8][8];
		Boards b = repo.findByWhiteId(userId);
		if(b == null) b = repo.findByBlackId(userId);
		if (repo.findByWhiteId(userId) != null) {
			String sBoard = b.getfenNotation();
			String fen = sBoard.split(" ")[0];
			int row = 7;
			int col = 0;
			for (int i = 0; i < fen.length(); i++) {
				char x = fen.charAt(i);
				String color = null;
				String type = null;
				if (x == 'p') {
					type = "Pawn";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'r') {
					type = "Rook";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'n') {
					type = "Knight";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'b') {
					type = "Bishop";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'k') {
					type = "King";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'q') {
					type = "Queen";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				}else if (x == 'P'){
					type = "Pawn";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				}else if (x == 'R') {
					type = "Rook";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'N') {
					type = "Knight";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'B') {
					type = "Bishop";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'K') {
					type = "King";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'Q') {
					type = "Queen";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				}else if (x == '/'){
					row--;
					col = 0;
				}else{
					int xx = x - '0';
					for (int ii = 0; ii < xx; ii++){
						board[row][col] = new Piece(color, type, row, col++);
					}
				}
				
			}
		} else if (repo.findByBlackId(userId) != null) {
			b = repo.findByBlackId(userId);
			String sBoard = b.getfenNotation();
			String fen = sBoard.split(" ")[0];
			int row = 0;
			int col = 0;
			for (int i = 0; i < fen.length(); i++) {
				char x = fen.charAt(i);
				String color = null;
				String type = null;
				if (x == 'p') {
					type = "Pawn";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'r') {
					type = "Rook";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'n') {
					type = "Knight";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'b') {
					type = "Bishop";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'k') {
					type = "King";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'q') {
					type = "Queen";
					color = "White";
					board[row][col] = new Piece(color, type, row, col++);
				}else if (x == 'P'){
					type = "Pawn";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				}else if (x == 'R') {
					type = "Rook";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'N') {
					type = "Knight";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'B') {
					type = "Bishop";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'K') {
					type = "King";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				} else if (x == 'Q') {
					type = "Queen";
					color = "Black";
					board[row][col] = new Piece(color, type, row, col++);
				}else if (x == '/'){
					row++;
					col = 0;
				}else{
					int xx = x-'0';
					for (int ii = 0; ii < xx; ii++){
						board[row][col] = new Piece(color, type, row, col++);
					}
				}
				
			}
		} else {
			// row
			for (int i = 0; i < 8; i++) {
				// col
				for (int j = 0; j < 8; j++) {
					board[i][j] = new Piece("null", "null", i, j);
				}
			}
		}
		return board;
	}

	@Override
	public void chessMove(Move move) {
		Boards b;
		b = repo.findByWhiteId(move.getUserId());
		boolean white = true;
		if(b ==null){
			b = repo.findByBlackId(move.getUserId());
			white = false;
		}
		if(!white){
			move.setYourRow(7-move.getYourRow());
			move.setThierRow(7-move.getThierRow());
		}
		System.out.printf("thier piece at: %d %d, your piece at: %d %d/n", move.getThierCol(), move.getThierRow(), move.getYourCol(), move.getYourRow());
		Board board = new Board();
		board.loadFromFen(b.getfenNotation());

		Square from = null;
		Square to = null;
		switch(move.getYourCol()){
			case 0:
				switch(move.getYourRow()){
					case 0:
						from = Square.A1;
						break;
					case 1:
						from = Square.A2;
						break;
					case 2:
						from = Square.A3;
						break;
					case 3:
						from = Square.A4;
						break;
					case 4:
						from = Square.A5;
						break;
					case 5:
						from = Square.A6;
						break;
					case 6:
						from = Square.A7;
						break;
					case 7:
						from = Square.A8;
						break;
				}
				break;
			case 1:
				switch(move.getYourRow()){
					case 0:
						from = Square.B1;
						break;
					case 1:
						from = Square.B2;
						break;
					case 2:
						from = Square.B3;
						break;
					case 3:
						from = Square.B4;
						break;
					case 4:
						from = Square.B5;
						break;
					case 5:
						from = Square.B6;
						break;
					case 6:
						from = Square.B7;
						break;
					case 7:
						from = Square.B8;
						break;
				}
				break;
			case 2:
				switch(move.getYourRow()){
					case 0:
						from = Square.C1;
						break;
					case 1:
						from = Square.C2;
						break;
					case 2:
						from = Square.C3;
						break;
					case 3:
						from = Square.C4;
						break;
					case 4:
						from = Square.C5;
						break;
					case 5:
						from = Square.C6;
						break;
					case 6:
						from = Square.C7;
						break;
					case 7:
						from = Square.C8;
						break;
				}
				break;
			case 3:
				switch(move.getYourRow()){
					case 0:
						from = Square.D1;
						break;
					case 1:
						from = Square.D2;
						break;
					case 2:
						from = Square.D3;
						break;
					case 3:
						from = Square.D4;
						break;
					case 4:
						from = Square.D5;
						break;
					case 5:
						from = Square.D6;
						break;
					case 6:
						from = Square.D7;
						break;
					case 7:
						from = Square.D8;
						break;
				}
				break;
			case 4:
				switch(move.getYourRow()){
					case 0:
						from = Square.E1;
						break;
					case 1:
						from = Square.E2;
						break;
					case 2:
						from = Square.E3;
						break;
					case 3:
						from = Square.E4;
						break;
					case 4:
						from = Square.E5;
						break;
					case 5:
						from = Square.E6;
						break;
					case 6:
						from = Square.E7;
						break;
					case 7:
						from = Square.E8;
						break;
				}
				break;
			case 5:
				switch(move.getYourRow()){
					case 0:
						from = Square.F1;
						break;
					case 1:
						from = Square.F2;
						break;
					case 2:
						from = Square.F3;
						break;
					case 3:
						from = Square.F4;
						break;
					case 4:
						from = Square.F5;
						break;
					case 5:
						from = Square.F6;
						break;
					case 6:
						from = Square.F7;
						break;
					case 7:
						from = Square.F8;
						break;
				}
				break;
			case 6:
				switch(move.getYourRow()){
					case 0:
						from = Square.G1;
						break;
					case 1:
						from = Square.G2;
						break;
					case 2:
						from = Square.G3;
						break;
					case 3:
						from = Square.G4;
						break;
					case 4:
						from = Square.G5;
						break;
					case 5:
						from = Square.G6;
						break;
					case 6:
						from = Square.G7;
						break;
					case 7:
						from = Square.G8;
						break;
				}
				break;
			case 7:
				switch(move.getYourRow()){
					case 0:
						from = Square.H1;
						break;
					case 1:
						from = Square.H2;
						break;
					case 2:
						from = Square.H3;
						break;
					case 3:
						from = Square.H4;
						break;
					case 4:
						from = Square.H5;
						break;
					case 5:
						from = Square.H6;
						break;
					case 6:
						from = Square.H7;
						break;
					case 7:
						from = Square.H8;
						break;
				}
				break;
		}
		switch(move.getThierCol()){
			case 0:
				switch(move.getThierRow()){
					case 0:
						to = Square.A1;
						break;
					case 1:
						to = Square.A2;
						break;
					case 2:
						to = Square.A3;
						break;
					case 3:
						to = Square.A4;
						break;
					case 4:
						to = Square.A5;
						break;
					case 5:
						to = Square.A6;
						break;
					case 6:
						to = Square.A7;
						break;
					case 7:
						to = Square.A8;
						break;
				}
				break;
			case 1:
				switch(move.getThierRow()){
					case 0:
						to = Square.B1;
						break;
					case 1:
						to = Square.B2;
						break;
					case 2:
						to = Square.B3;
						break;
					case 3:
						to = Square.B4;
						break;
					case 4:
						to = Square.B5;
						break;
					case 5:
						to = Square.B6;
						break;
					case 6:
						to = Square.B7;
						break;
					case 7:
						to = Square.B8;
						break;
				}
				break;
			case 2:
				switch(move.getThierRow()){
					case 0:
						to = Square.C1;
						break;
					case 1:
						to = Square.C2;
						break;
					case 2:
						to = Square.C3;
						break;
					case 3:
						to = Square.C4;
						break;
					case 4:
						to = Square.C5;
						break;
					case 5:
						to = Square.C6;
						break;
					case 6:
						to = Square.C7;
						break;
					case 7:
						to = Square.C8;
						break;
				}
				break;
			case 3:
				switch(move.getThierRow()){
					case 0:
						to = Square.D1;
						break;
					case 1:
						to = Square.D2;
						break;
					case 2:
						to = Square.D3;
						break;
					case 3:
						to = Square.D4;
						break;
					case 4:
						to = Square.D5;
						break;
					case 5:
						to = Square.D6;
						break;
					case 6:
						to = Square.D7;
						break;
					case 7:
						to = Square.D8;
						break;
				}
				break;
			case 4:
				switch(move.getThierRow()){
					case 0:
						to = Square.E1;
						break;
					case 1:
						to = Square.E2;
						break;
					case 2:
						to = Square.E3;
						break;
					case 3:
						to = Square.E4;
						break;
					case 4:
						to = Square.E5;
						break;
					case 5:
						to = Square.E6;
						break;
					case 6:
						to = Square.E7;
						break;
					case 7:
						to = Square.E8;
						break;
				}
				break;
			case 5:
				switch(move.getThierRow()){
					case 0:
						to = Square.F1;
						break;
					case 1:
						to = Square.F2;
						break;
					case 2:
						to = Square.F3;
						break;
					case 3:
						to = Square.F4;
						break;
					case 4:
						to = Square.F5;
						break;
					case 5:
						to = Square.F6;
						break;
					case 6:
						to = Square.F7;
						break;
					case 7:
						to = Square.F8;
						break;
				}
				break;
			case 6:
				switch(move.getThierRow()){
					case 0:
						to = Square.G1;
						break;
					case 1:
						to = Square.G2;
						break;
					case 2:
						to = Square.G3;
						break;
					case 3:
						to = Square.G4;
						break;
					case 4:
						to = Square.G5;
						break;
					case 5:
						to = Square.G6;
						break;
					case 6:
						to = Square.G7;
						break;
					case 7:
						to = Square.G8;
						break;
				}
				break;
			case 7:
				switch(move.getThierRow()){
					case 0:
						to = Square.H1;
						break;
					case 1:
						to = Square.H2;
						break;
					case 2:
						to = Square.H3;
						break;
					case 3:
						to = Square.H4;
						break;
					case 4:
						to = Square.H5;
						break;
					case 5:
						to = Square.H6;
						break;
					case 6:
						to = Square.H7;
						break;
					case 7:
						to = Square.H8;
						break;
				}
				break;
		}
    System.out.println(board.toString());
	System.out.println(from);
	System.out.println(to);
		System.out.println(board.doMove(new com.github.bhlangonijr.chesslib.move.Move(from,to)));
    System.out.println(board.toString());
		b.setfenNotation(board.getFen());
		b.setTurn(b.getTurn()+1);
		repo.save(b);
	}

	@Override
	public int[] getBoards() {
		List<Boards> opt = repo.findAll();
		List<Integer> boards = new ArrayList<Integer>();
		for(int i = 0; i < opt.size(); i++) {
			if(opt.get(i).getBlackId() == null || opt.get(i).getWhiteId() == null )
			boards.add(opt.get(i).getBoardId());
		}
		/*Piece[][][] boards = new Piece[opt.size()][][];
		for (int j = 0; j < opt.size(); j++) {
			Boards b = opt.get(j);
			Piece[][] board = new Piece[8][8];
			String sBoard = b.getBoardEncode();
			for (int i = 0; i < sBoard.length(); i += 2) {
				char x = sBoard.charAt(i);
				char y = sBoard.charAt(i + 1);
				String color = null;
				if (y == 'b') {
					color = "Black";
				} else if (y == 'w') {
					color = "White";
				}
				String type = null;
				if (x == 'p') {
					type = "Pawn";
				} else if (x == 'r') {
					type = "Rook";
				} else if (x == 'n') {
					type = "Knight";
				} else if (x == 'b') {
					type = "Bishop";
				} else if (x == 'k') {
					type = "King";
				} else if (x == 'q') {
					type = "Queen";
				}
				board[i / 8 / 2][i / 2 % 8] = new Piece(color, type, i / 8 / 2, i / 2 % 8);
			}
			boards[j] = board;
		}*/
		int[] rooms= new int[boards.size()];
		for(int i = 0; i < boards.size(); i++) {
			rooms[i] = (int)boards.get(i);
		}
		return rooms;
	}

	public Integer getTurn(String userId) {
		Boards board = repo.findByWhiteId(userId);
		if (board != null) {
			return board.getTurn();
		}
		board = repo.findByBlackId(userId);
		if (board != null) {
			return board.getTurn();
		}
		return 0;
	}

	public Boolean joinRoom(Integer boardId, String userId) {
		Optional<Boards> b = repo.findById(boardId);
		Boards board = b.orElseThrow();
		
		if(board.getWhiteId() == userId) {
			return false;
		}
		board.setBlackId(userId);
		repo.save(board);
		return true;
	}

	@Override
	public void gameOver(String userId) {
		if(repo.findByWhiteId(userId) != null) {
			repo.delete(repo.findByWhiteId(userId));
		}
		else if(repo.findByBlackId(userId) != null){
			repo.delete(repo.findByBlackId(userId));
		}
	}
}
