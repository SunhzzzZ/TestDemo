package com.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.test.dao.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE USER_ID = #{userId} AND DEL_FLG <> 1")
    User getUserByPrimaryKey(@Param("userId") String userID);

    @Select("SELECT * FROM USER A WHERE A.DEL_FLG <> 1")
    List<User> getAllUser();

    int insertSingleUser(User user);

    User login(String userId, String password);
}
