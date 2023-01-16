package com.example.practice.board.common.model;

import com.example.practice.board.model.ServiceResult;
import com.example.practice.user.model.ResponseMessage;
import org.springframework.http.ResponseEntity;

public class ResponseResult {

    public static ResponseEntity<?> fail(String message) {
        return ResponseEntity.badRequest().body(ResponseMessage.fail(message));
    }

    public static ResponseEntity<?> success() {
        return ResponseEntity.ok(ResponseMessage.success());
    }

    public static ResponseEntity<?> result(ServiceResult result) {
        if (result.isFail()) {
            return fail(result.getMessage());
        }
        return success();
    }
}
