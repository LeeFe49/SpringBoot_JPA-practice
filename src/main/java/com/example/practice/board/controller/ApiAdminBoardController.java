package com.example.practice.board.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.practice.board.common.model.ResponseResult;
import com.example.practice.board.entity.BoardBadReport;
import com.example.practice.board.entity.BoardType;
import com.example.practice.board.model.BoardBadReportInput;
import com.example.practice.board.model.BoardPeriod;
import com.example.practice.board.model.BoardTypeCount;
import com.example.practice.board.model.BoardTypeInput;
import com.example.practice.board.model.BoardTypeUsing;
import com.example.practice.board.model.ServiceResult;
import com.example.practice.board.service.BoardService;
import com.example.practice.notice.model.ResponseError;
import com.example.practice.user.model.ResponseMessage;
import com.example.practice.util.JWTUtils;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiAdminBoardController {

    private final BoardService boardService;

    @GetMapping("/api/admin/board/badreport")
    public ResponseEntity<?> badReport() {

        List<BoardBadReport> list = boardService.badReportList();
        return ResponseResult.success(list);
    }
}
