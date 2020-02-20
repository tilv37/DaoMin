package com.haramasu.daomin.service.impl;

import com.haramasu.daomin.Daomin3ApplicationTests;
import com.haramasu.daomin.service.TranslateService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class BaiduTranslateServiceImplTest extends Daomin3ApplicationTests {

    @Autowired
    private TranslateService BaiduTranslateServiceImpl;

    @Test
    public void translate() {
        String translate = BaiduTranslateServiceImpl.translate("如何进行数据库操作");
        System.out.println(translate);
    }
}