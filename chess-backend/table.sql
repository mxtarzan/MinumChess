drop database if exists chess_db;
create database chess_db;
USE chess_db;

create table boards(
   board_id  INT primary key AUTO_INCREMENT,
   fen_notation VARCHAR(256) DEFAULT 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1' not null,
   white_id VARCHAR(100),
   black_id VARCHAR(100),
   turn INT DEFAULT 0
);
commit;
