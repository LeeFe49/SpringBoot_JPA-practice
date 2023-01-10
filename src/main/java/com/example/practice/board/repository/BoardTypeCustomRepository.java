package com.example.practice.board.repository;

import com.example.practice.board.model.BoardTypeCount;
import com.example.practice.user.model.UserNoticeCount;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardTypeCustomRepository {

    private final EntityManager entityManager;

    public List<BoardTypeCount> getBoardTypeCount() {

        String sql = "select bt.id, bt.board_name, bt.reg_date, bt.using_yn " +
            ",(select count(*) from board b where b.board_type_id = bt.id) as board_count " +
            "from board_type bt";

//        List<BoardTypeCount> list = entityManager.createNativeQuery(sql).getResultList();

        List<Object[]> result = entityManager.createNativeQuery(sql).getResultList();
        List<BoardTypeCount> resultList = result.stream().map(e-> new BoardTypeCount(e))
            .collect(Collectors.toList());

        return resultList;
    }
}
