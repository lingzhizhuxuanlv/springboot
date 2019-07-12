package com.lingzhizhuxuanlv.springboot.service;

import com.lingzhizhuxuanlv.springboot.model.User;

public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
