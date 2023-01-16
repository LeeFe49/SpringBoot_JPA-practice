package com.example.practice.board.entity;

import com.example.practice.user.entity.User;
import java.time.LocalDate;
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
public class BoardBadReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //신고자
    @Column
    private long userId;

    @Column
    private String userName;

    @Column
    private String userEmail;

    //신고게시글
    @Column
    private long boardId;

    @Column
    private long boardUserId;

    @Column
    private String boardContents;

    @Column
    private String boardTitle;

    @Column
    private LocalDateTime boardRegDate;

    //신고내용
    @Column
    private String comments;

    @Column
    private LocalDateTime regDate;
}
