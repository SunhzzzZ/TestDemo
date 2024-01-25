package com.test.service;

import com.test.dao.User;

import java.util.List;

public interface UserService {

    /**
     * 获取用户
     * @param userID 用户ID
     * @return 指定用户
     */
    User getUserByPrimaryKey(String userID);

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllUser();

    /**
     * 插入单个用户
     * @param user
     * @return 是否成功 1：成功  ELSE：失败
     */
    int insertSingleUser(User user);

    User login(String userId, String password);
}
