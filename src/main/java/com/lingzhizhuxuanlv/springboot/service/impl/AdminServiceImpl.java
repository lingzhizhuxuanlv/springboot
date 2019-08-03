package com.lingzhizhuxuanlv.springboot.service.impl;

import com.lingzhizhuxuanlv.springboot.dao.AdminMapper;
import com.lingzhizhuxuanlv.springboot.model.Admin;
import com.lingzhizhuxuanlv.springboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper dao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Admin record) {
        return dao.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return dao.insertSelective(record);
    }

    @Override
    public Admin selectByPrimaryKey(Long id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return dao.updateByPrimaryKey(record);
    }

}
