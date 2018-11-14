package com.vens.service;

import com.vens.mapper.UserMapper;
import com.vens.pojos.User;
import com.vens.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/11/12
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserMapper userMapper;

    public Long addUser(User user) {
        logger.info("register:{}", user);
        Long id = (long)userMapper.add(user);
        return id;
    }

    public String login(String phone, String password) {
        logger.info("login:{}", phone);
        CheckUtil.notEmpty(password, "电话为空");
        List<User> user = new ArrayList<>();
        try{
            user=userMapper.getByPhone(phone);
        }catch (Exception e){
            e.printStackTrace();
        }
        CheckUtil.notNull(user.get(0), "result为空");
        if (!password.equals(user.get(0).getPassword())) {
            CheckUtil.check(false, "电话为空");
        }
        return "token:HDJDJLDFCSHJ**))C)S5";
    }
}
