package com.example.practice.notice.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NoticeInput {

    @Size(min = 10, max = 100, message = "제목은 10-100자 사이의 값입니다.")
    @NotBlank(message = "제목은 필수 항목입니다.")
    private String title;

    @Size(min = 50, max = 1000, message = "내용은 50-1000자 사이의 값입니다.")
    @NotBlank(message = "내용은 필수 항목입니다.")
    private String contents;
}
