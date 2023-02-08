package com.example.practice.board.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.practice.board.common.model.ResponseResult;
import com.example.practice.board.service.BoardService;
import com.example.practice.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiBoardBookmarkController {

    private final BoardService boardService;

    @PutMapping("/api/board/{id}/bookmark")
    public ResponseEntity<?> boardBookmark(@PathVariable Long id
    ,@RequestHeader("F-TOKEN") String token) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }
        return ResponseResult.result(boardService.addBookmark(id, email));
    }

    @DeleteMapping("/api/bookmark/{id}")
    public ResponseEntity<?> deleteBookmark(@PathVariable Long id,@RequestHeader("F-TOKEN") String token) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }
        return ResponseResult.result(boardService.removeBookmark(id, email));
    }
}
