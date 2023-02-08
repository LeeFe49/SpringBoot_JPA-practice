package com.example.practice.board.repository;

import com.example.practice.board.entity.BoardBookmark;
import com.example.practice.board.entity.BoardScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardBookmarkRepository extends JpaRepository<BoardBookmark, Long> {

}
