package com.example.practice.board.common.model;

import com.example.practice.board.entity.BoardBadReport;
import com.example.practice.board.model.ServiceResult;
import com.example.practice.user.model.ResponseMessage;
import java.util.List;
import org.springframework.http.ResponseEntity;

public class ResponseResult {

    public static ResponseEntity<?> fail(String message) {
        return ResponseEntity.badRequest().body(ResponseMessage.fail(message));
    }

    public static ResponseEntity<?> success() {
        return success(null);
    }

    public static ResponseEntity<?> result(ServiceResult result) {
        if (result.isFail()) {
            return fail(result.getMessage());
        }
        return success();
    }

    public static ResponseEntity<?> success(List<BoardBadReport> data) {
        return ResponseEntity.ok(ResponseMessage.success(data));
    }
}
