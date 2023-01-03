package com.example.practice.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserNoticeCount {

    private long id;
    private String email;
    private String userName;

    private long noticeCount;
}
