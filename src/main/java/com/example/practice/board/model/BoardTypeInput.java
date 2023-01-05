package com.example.practice.board.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardTypeInput {

    @NotBlank(message = "게시판 제목은 픽수 항목입니다.")
    private String name;
}
