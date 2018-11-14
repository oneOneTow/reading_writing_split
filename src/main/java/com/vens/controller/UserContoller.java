package com.vens.controller;

import com.vens.beans.ResultBean;
import com.vens.pojos.User;
import com.vens.service.UserService;
import com.vens.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/11/12
 */
@Controller
@RequestMapping("/user")
public class UserContoller {

    @Autowired
    private UserService userService;

    @RequestMapping(
            path = "/register.do",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResultBean<Long> register(@RequestBody User user){
        return new ResultBean<>(userService.addUser(user));
    }
    @RequestMapping(
            path = "/login.do",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResultBean<String> login(@RequestBody UserVo userVo){
        return new ResultBean<>(userService.login(userVo.getPhone(),userVo.getPassword()));
    }
}
