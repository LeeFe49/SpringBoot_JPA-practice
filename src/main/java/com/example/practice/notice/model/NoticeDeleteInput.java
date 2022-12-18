package com.example.practice.notice.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NoticeDeleteInput {

    private List<Long> idList;

}
