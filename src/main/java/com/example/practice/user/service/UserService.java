package com.example.practice.user.service;


import com.example.practice.user.entity.User;
import com.example.practice.user.model.UserNoticeCount;
import com.example.practice.user.model.UserSummary;

import java.util.List;

public interface UserService {

    UserSummary getUserStatusCount();

    List<User> getTodayUsers();

    List<UserNoticeCount> getUserNoticeCount();
}
