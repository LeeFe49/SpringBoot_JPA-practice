package com.example.practice.board.repository;

import com.example.practice.board.entity.Board;
import com.example.practice.board.entity.BoardHits;
import com.example.practice.board.entity.BoardType;
import com.example.practice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardHitsRepository extends JpaRepository<BoardHits, Long> {

    long countByBoardAndUser(Board board, User user);
}
