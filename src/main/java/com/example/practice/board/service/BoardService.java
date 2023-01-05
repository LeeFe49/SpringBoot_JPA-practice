package com.example.practice.board.service;

import com.example.practice.board.entity.BoardType;
import com.example.practice.board.model.BoardTypeInput;
import com.example.practice.board.model.ServiceResult;

public interface BoardService {

    ServiceResult addBoard(BoardTypeInput boardTypeInput);
}
