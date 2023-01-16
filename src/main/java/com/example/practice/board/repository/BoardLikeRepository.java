package com.example.practice.board.repository;

import com.example.practice.board.entity.Board;
import com.example.practice.board.entity.BoardHits;
import com.example.practice.board.entity.BoardLike;
import com.example.practice.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    long countByBoardAndUser(Board board, User user);

    Optional<BoardLike> findByBoardAndUser(Board board, User user);
}
