package com.example.practice.board.entity;

import com.example.practice.user.entity.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardScrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private User user;

    //스크랩글정보
    @Column
    private long boardId;

    @Column
    private long boardTypeId;

    @Column
    private long boardUserId;

    @Column
    private String boardContents;

    @Column
    private String boardTitle;

    @Column
    private LocalDateTime boardRegDate;

    @Column
    private LocalDateTime regDate;
}
