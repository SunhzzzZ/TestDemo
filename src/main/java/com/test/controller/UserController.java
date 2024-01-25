package com.test.controller;

import com.test.contant.STATUS_FLG_ENUM;
import com.test.dao.User;
import com.test.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController()
public class UserController {

    @Autowired
    UserService userService;

    /**
     *
     * @return
     */
    @GetMapping("/")
    public String defaultNoAuthUrl(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId,
                        @RequestParam String password){

        User user = userService.login(userId,password);

        // TODO 无此用户，待完善{用户名密码错误/账号冻结/无此账号}
        if(null == user){
            return "登录失败";
        }
        // 登录成功
        else {
            // 跳转到主页
            String userName = user.getUserName();   // 用户名
            String sex = user.getSex();             // 性别
            return "home主页";
        }
    }

    /**
     * 获取单个用户API
     * @param userId
     * @return
     */
    @RequiresRoles("admin")
    @PostMapping("/user/get/{userId}")
    public User getUser(@PathVariable("userId") String userId){

        return userService.getUserByPrimaryKey(userId);
    }

    /**
     * 获取所有用户API
     * @return
     */
    @GetMapping("/all")
    public List<User> aa(){
        return userService.getAllUser();
    }

    /**
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public STATUS_FLG_ENUM insertUser(@RequestBody() User user){

        int resultFlg = userService.insertSingleUser(user);
        if(0 != resultFlg) {
            return STATUS_FLG_ENUM.SUCCESS;
        } else {
            return STATUS_FLG_ENUM.ERROR;
        }
    }
}
