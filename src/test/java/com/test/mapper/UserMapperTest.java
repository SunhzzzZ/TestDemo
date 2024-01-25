package com.test.mapper;

import com.test.dao.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    User user = new User();

    @Before
    public void setUp() throws Exception {
        Date date = new Date(System.currentTimeMillis());
        user.setUserId("0000000004");
        user.setUserName("LiMing");
        user.setSex("1");
        user.setDelFlg("0");
        user.setInsertId("0000000004");
        user.setInsertTime(date);
        user.setUpdateId("0000000004");
        user.setUpdateTime(date);
    }

    @Test
    public void insertSingleUser() {
        String insertId = user.getInsertId();
        String userName = user.getUserName();
        Date insertTime = user.getInsertTime();
        int i = userMapper.insertSingleUser(user);
        System.out.println("插入是否成功： "+i);
    }
}