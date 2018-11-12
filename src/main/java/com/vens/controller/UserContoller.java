package com.vens.controller;

import com.vens.beans.ResultBean;
import com.vens.pojos.User;
import com.vens.service.UserService;
import com.vens.vo.UserVo;
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

    private UserService userService;
    @RequestMapping(
            name = "/register",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResultBean<Long> register(@RequestBody User user){
        return new ResultBean<Long>(userService.addUser(user));
    }
    @RequestMapping(name = "/login",method = RequestMethod.POST)
    public ResultBean<String> login(UserVo userVo){
        return new ResultBean<String>(userService.login(userVo.getName(),userVo.getPassword()));
    }
}
