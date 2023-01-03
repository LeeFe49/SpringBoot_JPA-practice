package com.example.practice.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserSummary {

    private long stopUserCount;
    private long usingUserCount;
    private long totalUserCount;
}
