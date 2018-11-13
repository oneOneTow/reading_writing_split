package com.vens.service;

import com.vens.mapper.UserMapper;
import com.vens.pojos.User;
import com.vens.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        CheckUtil.notNull(password, "密码不对");
        CheckUtil.notNull(phone, "电话为空");
        User user = userMapper.getByPhone(phone);
        if (!password.equals(user.getPassword())) {
            CheckUtil.check(false, "密码不对");
        }
        return "token:HDJDJLDFCSHJ**))C)S5";
    }
}
