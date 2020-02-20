package com.haramasu.daomin.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.haramasu.daomin.service.TranslateService;
import com.haramasu.daomin.util.OkHttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/20/2020
 */
@Service
public class BaiduTranslateServiceImpl implements TranslateService {

    @Value("${baidu.translate.api}")
    private String BAIDU_API;
    @Value("${baidu.app.id}")
    private String BAIDU_API_ID;
    @Value("${baidu.app.slat}")
    private String BAIDU_API_SALT;

    @Autowired
    private OkHttpClientUtil okHttpClientUtil;

    /**
     * 中译英
     * @param sourceContent
     * @return
     */
    @Override
    public String translate(String sourceContent) {
        Map<String,String> params=new HashMap<>();
        params.put("q",sourceContent);
        params.put("from","zh");
        params.put("to","en");
        params.put("appid",BAIDU_API_ID);
        String randomStr= RandomUtil.randomNumbers(7);
        params.put("salt",randomStr);
        String tempSing=BAIDU_API_ID+sourceContent+randomStr+BAIDU_API_SALT;
        String sign = SecureUtil.md5(tempSing);
        params.put("sign",sign);
        String responseStr = okHttpClientUtil.doGet(BAIDU_API, params, null);
        if(StringUtils.isNotBlank(responseStr)){
            JSONObject jsonObject = JSONUtil.parseObj(responseStr);
            String error_code = jsonObject.getStr("error_code");
            if(StringUtils.isNotBlank(error_code)){
                return "";
            }
            JSONArray trans_result = jsonObject.getJSONArray("trans_result");
            return trans_result.getJSONObject(0).getStr("dst");
        }else{
            return responseStr;
        }
    }
}
