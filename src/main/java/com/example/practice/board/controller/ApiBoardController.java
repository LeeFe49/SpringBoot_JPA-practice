package com.example.practice.board.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.practice.board.common.model.ResponseResult;
import com.example.practice.board.entity.BoardType;
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
public class ApiBoardController {

    private final BoardService boardService;

    @PostMapping("/api/board/type")
    public ResponseEntity<ResponseMessage> addBoardType(@RequestBody @Valid BoardTypeInput boardTypeInput,
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

    @PutMapping("/api/board/type/{id}")
    public ResponseEntity<ResponseMessage> updateBoardType(@PathVariable Long id,
        @RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = ResponseError.of(errors.getAllErrors());
            return new ResponseEntity<>(ResponseMessage.fail("입력값이 정확하지 않습니다"),
                HttpStatus.BAD_REQUEST);
        }

        ServiceResult result = boardService.updateBoard(id, boardTypeInput);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/board/type/{id}")
    public ResponseEntity<ResponseMessage> deleteBoardType(@PathVariable Long id) {

        ServiceResult result = boardService.deleteBoard(id);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }
        return ResponseEntity.ok().body(ResponseMessage.success());
    }

    @GetMapping("/api/board/type")
    public ResponseEntity<ResponseMessage> boardType() {

        List<BoardType> boardTypeList = boardService.getAllBoardType();

        return ResponseEntity.ok().body(ResponseMessage.success(boardTypeList));
    }

    @PatchMapping("/api/board/type/{id}/using")
    public ResponseEntity<ResponseMessage> usingBoardType(@PathVariable Long id,
        @RequestBody BoardTypeUsing boardTypeUsing) {

        ServiceResult result = boardService.setBoardTypeUsing(id, boardTypeUsing);
        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().body(ResponseMessage.success());
    }

    @GetMapping("/api/board/type/count")
    public ResponseEntity<Object> boardTypeCount() {

        List<BoardTypeCount> list = boardService.getBoardTypeCount();
        return ResponseEntity.ok().body(list);
    }

    @PatchMapping("/api/board/{id}/top")
    public ResponseEntity<?> boardPostTop(@PathVariable Long id) {

        ServiceResult result = boardService.setBoardTop(id, true);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/board/{id}/top/Clear")
    public ResponseEntity<?> boardPostTopClear(@PathVariable Long id) {

        ServiceResult result = boardService.setBoardTop(id, false);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/board/{id}/publish")
    public ResponseEntity<?> boardPeriod(@PathVariable Long id, @RequestBody BoardPeriod boardPeriod) {

        ServiceResult result = boardService.setBoardPeriod(id, boardPeriod);

        if (!result.isResult()) {
            return ResponseResult.fail(result.getMessage());
        }

        return ResponseResult.success();
    }

    @PutMapping("/api/board/{id}/hits")
    public ResponseEntity<?> boardHits(@PathVariable Long id, @RequestHeader("F-TOKEN") String token) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        ServiceResult result = boardService.setBoardHits(id, email);
        if (result.isFail()) {
            return ResponseResult.fail(result.getMessage());
        }
        return ResponseResult.success();
    }
}
