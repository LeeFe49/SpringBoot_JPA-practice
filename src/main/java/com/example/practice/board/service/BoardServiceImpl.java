package com.example.practice.board.service;

import com.example.practice.board.entity.BoardType;
import com.example.practice.board.model.BoardTypeInput;
import com.example.practice.board.model.ServiceResult;
import com.example.practice.board.repository.BoardTypeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardTypeRepository boardTypeRepository;

    public ServiceResult addBoard(BoardTypeInput boardTypeInput) {

        BoardType boardType = boardTypeRepository.findByBoardName(boardTypeInput.getName());

        if (boardType != null && boardTypeInput.getName().equals(boardType.getBoardName())) {
            return ServiceResult.fail("이미 동일한 게시판이 존재합니다.");
        }

        BoardType addBoardType = BoardType.builder()
            .boardName(boardTypeInput.getName())
            .regDate(LocalDateTime.now())
            .build();

        boardTypeRepository.save(addBoardType);

        return ServiceResult.success();
    }
}
