package com.shawnchan.springboot.service.impl;

import com.shawnchan.springboot.dao.UserMapper;
import com.shawnchan.springboot.model.User;
import com.shawnchan.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return dao.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return dao.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return dao.updateByPrimaryKey(record);
    }

}