package com.example.practice.board.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardTypeCount {

    private long id;
    private String boardName;
    private LocalDateTime regDate;
    private boolean usingYn;
    private long boardCount;

    public BoardTypeCount(Object[] arrObj) {
        this.id = ((BigInteger) arrObj[0]).longValue();
        this.boardName = (String) arrObj[1];
        this.regDate = ((Timestamp) arrObj[2]).toLocalDateTime();
        this.usingYn = (Boolean) arrObj[3];
        this.boardCount = ((BigInteger) arrObj[4]).longValue();
    }
}
