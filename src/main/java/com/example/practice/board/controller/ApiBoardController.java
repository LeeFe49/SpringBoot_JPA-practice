package com.example.practice.board.controller;

import com.example.practice.board.entity.BoardType;
import com.example.practice.board.model.BoardTypeInput;
import com.example.practice.board.model.ServiceResult;
import com.example.practice.board.service.BoardService;
import com.example.practice.notice.entity.Notice;
import com.example.practice.notice.exception.AlreadyDeletedException;
import com.example.practice.notice.exception.DuplicateNoticeException;
import com.example.practice.notice.exception.NoticeNotFoundException;
import com.example.practice.notice.model.NoticeDeleteInput;
import com.example.practice.notice.model.NoticeInput;
import com.example.practice.notice.model.NoticeModel;
import com.example.practice.notice.model.ResponseError;
import com.example.practice.notice.repository.NoticeRepository;
import com.example.practice.user.model.ResponseMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiBoardController {

    private final BoardService boardService;

    @PostMapping("/api/board/type")
    public ResponseEntity<?> addBoardType(@RequestBody @Valid BoardTypeInput boardTypeInput,
        Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = ResponseError.of(errors.getAllErrors());
            return new ResponseEntity<>(ResponseMessage.fail("입력값이 정확하지 않습니다"),
                HttpStatus.BAD_REQUEST);
        }

        ServiceResult result = boardService.addBoard(boardTypeInput);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }
        return ResponseEntity.ok().build();
    }
}
