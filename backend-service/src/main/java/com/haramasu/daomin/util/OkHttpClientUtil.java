package com.haramasu.daomin.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/20/2020
 */
@Component
public class OkHttpClientUtil {

    private final Logger logger= LoggerFactory.getLogger(OkHttpClientUtil.class);

    @Autowired
    private OkHttpClient okHttpClient;

    public String doGet(String url, Map<String,String> params,String[] headers){
        StringBuilder sb = new StringBuilder(url);
        if (params != null && params.keySet().size() > 0) {
            boolean firstFlag = true;
            for (String key : params.keySet()) {
                if (firstFlag) {
                    sb.append("?").append(key).append("=").append(params.get(key));
                    firstFlag = false;
                } else {
                    sb.append("&").append(key).append("=").append(params.get(key));
                }
            }
        }

        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.length > 0) {
            if (headers.length % 2 == 0) {
                for (int i = 0; i < headers.length; i = i + 2) {
                    builder.addHeader(headers[i], headers[i + 1]);
                }
            } else {
                logger.warn("headers's length[{}] is error.", headers.length);
            }

        }

        Request request = builder.url(sb.toString()).build();
        logger.info("do get request and url[{}]", sb.toString());
        return execute(request);
    }

    private String execute(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }
}
