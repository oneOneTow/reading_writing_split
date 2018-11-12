package com.vens.service;

import com.vens.mapper.UserMapper;
import com.vens.pojos.User;
import com.vens.utils.CheckUtil;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/11/12
 */
public class UserService {
    UserMapper userMapper;
    public Long addUser(User user){
        return userMapper.add(user);
    }
    public String login(String phone,String password){
        CheckUtil.notNull(password,"���벻��");
        CheckUtil.notNull(phone,"�绰Ϊ��");
        User user=userMapper.getByPhone(phone);
        if(!password.equals(user.getPassword())){
            CheckUtil.check(false,"���벻��");
        }
        return "token:HDJDJLDFCSHJ**))C)S5";
    }
}
