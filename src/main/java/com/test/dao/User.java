package com.test.dao;


import lombok.Data;

import java.sql.Date;

@Data
public class User {

    private String userId;

    private String userName;

    private String sex;

    private String delFlg;

    private String insertId;

    private Date insertTime;

    private String updateId;

    private Date updateTime;
}
