package com.example.practice.notice.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NoticeModel {
    private int Id;
    private String title;
    private String contents;
    private LocalDateTime regDate;
}
