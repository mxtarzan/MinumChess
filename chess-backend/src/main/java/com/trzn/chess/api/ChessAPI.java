package com.trzn.chess.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trzn.chess.DTO.Move;
import com.trzn.chess.entity.Boards;
import com.trzn.chess.service.ChessService;

@RestController
@RequestMapping(value = "ChessAPI")
@CrossOrigin
public class ChessAPI {

	@Autowired
	ChessService service;

	@GetMapping(value = "getBoard/{userId}")
	public ResponseEntity<Boards> getBoard(@PathVariable String userId) {
		return new ResponseEntity<Boards>(service.getBoard(userId), HttpStatus.OK);
	}

	@GetMapping(value = "getBoards")
	public ResponseEntity<int[]> getBoards() {
		return new ResponseEntity<int[]>(service.getBoards(), HttpStatus.OK);
	}

	@GetMapping(value = "createRoom/{userId}")
	public ResponseEntity<Boolean> createRoom(@PathVariable String userId) {
		System.out.println("creating room for userid: " + userId);
		return new ResponseEntity<Boolean>(service.createRoom(userId), HttpStatus.CREATED);
	}

	@GetMapping(value = "joinRoom/{boardId}/{userId}")
	public ResponseEntity<Boolean> joinRoom(@PathVariable Integer boardId, @PathVariable String userId) {
		return new ResponseEntity<Boolean>(service.joinRoom(boardId, userId), HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "chessMove")
	public ResponseEntity<Boards> chessMove(@RequestBody Move move) {
		service.chessMove(move);
		return new ResponseEntity<Boards>(service.getBoard(move.getUserId()), HttpStatus.OK);
	}
}
