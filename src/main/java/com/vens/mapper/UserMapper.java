package com.vens.mapper;

import com.vens.annotion.DataSource;
import com.vens.pojos.User;

import java.util.List;

public interface UserMapper {
    @DataSource("master")
    public int add(User user);
    @DataSource("slave")
    public List<User> getByPhone(String phone);

}
