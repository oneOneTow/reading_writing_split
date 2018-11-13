package com.vens.mapper;

import com.vens.annotion.DataSource;
import com.vens.pojos.User;

public interface UserMapper {
    @DataSource("master")
    public int add(User user);
    @DataSource("slave")
    public User getByPhone(String phone);

}
