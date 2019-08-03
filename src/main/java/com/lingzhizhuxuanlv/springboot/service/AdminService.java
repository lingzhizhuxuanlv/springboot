package com.lingzhizhuxuanlv.springboot.service;

import com.lingzhizhuxuanlv.springboot.model.Admin;

public interface AdminService {

    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

}
