package com.lingzhizhuxuanlv.springboot;

import com.lingzhizhuxuanlv.springboot.dao.AdminMapper;
import com.lingzhizhuxuanlv.springboot.model.Admin;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {

    @Autowired
    private AdminMapper dao;

    @Before
    public void before(){
        System.out.println("------开始测试------");
    }

    @Test
    public void doTest() {
        Admin as = dao.selectByPrimaryKey(1L);
        System.out.println(as);
        Assert.assertEquals("aaa","aaa");
    }

    @After
    public void after(){
        System.out.println("------测试结束------");
    }

}
