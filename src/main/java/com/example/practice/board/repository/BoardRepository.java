package com.example.practice.board.repository;

import com.example.practice.board.entity.Board;
import com.example.practice.board.entity.BoardType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    long countByBoardType(BoardType boardType);
}
