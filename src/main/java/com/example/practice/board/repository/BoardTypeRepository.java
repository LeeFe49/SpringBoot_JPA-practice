package com.example.practice.board.repository;

import com.example.practice.board.entity.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTypeRepository extends JpaRepository<BoardType, Long> {

    BoardType findByBoardName(String name);
}
