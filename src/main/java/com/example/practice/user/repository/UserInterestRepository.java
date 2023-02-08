package com.example.practice.user.repository;

import com.example.practice.user.entity.User;
import com.example.practice.user.entity.UserInterest;
import com.example.practice.user.model.UserStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {

    long countByUserAndInterestUser(User user, User interestUser);
}
