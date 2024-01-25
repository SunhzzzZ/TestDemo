package com.test.service.impl;

import com.test.dao.User;
import com.test.mapper.UserMapper;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务Service
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 根据主键获取ID
     * @param userID 用户ID
     * @return 用户对象
     */
    @Override
    public User getUserByPrimaryKey(String userID) {
        return userMapper.getUserByPrimaryKey(userID);
    }

    /**
     * 获取所有用户
     * @return 用户List
     */
    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public int insertSingleUser(User user) {
        try {
            return userMapper.insertSingleUser(user);
        } catch (Exception exception) {
            return 0;
        }
    }

    @Override
    public User login(String userId, String password) {
        return userMapper.login(userId,password);
    }
}
