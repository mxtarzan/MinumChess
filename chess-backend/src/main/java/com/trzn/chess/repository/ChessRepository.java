package com.trzn.chess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trzn.chess.entity.Boards;

public interface ChessRepository extends JpaRepository<Boards, Integer>{
	@Query("select b from Boards b where b.whiteId = ?1")
	public Boards findByWhiteId(String whiteId);
	@Query("select b from Boards b where b.blackId = ?1")
	public Boards findByBlackId(String blackId);
	
}
