package com.example.practice.board.repository;

import com.example.practice.board.entity.BoardComment;
import com.example.practice.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

    List<BoardComment> findByUser(User user);
}
