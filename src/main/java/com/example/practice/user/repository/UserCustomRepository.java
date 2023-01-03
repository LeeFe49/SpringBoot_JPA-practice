package com.example.practice.user.repository;

import com.example.practice.user.entity.User;
import com.example.practice.user.model.UserNoticeCount;
import com.example.practice.user.model.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserCustomRepository {

    private final EntityManager entityManager;

    public List<UserNoticeCount> findUserNoticeCount() {

        String sql = "select u.id, u.email, u.user_name, (select count(*) from Notice n where n.user_id = u.id) notice_count from User u";

        List<UserNoticeCount> list = entityManager.createNativeQuery(sql).getResultList();
        return list;
    }
}
