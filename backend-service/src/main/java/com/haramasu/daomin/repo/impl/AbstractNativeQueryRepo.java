package com.haramasu.daomin.repo.impl;

import javax.persistence.Query;
import java.util.Map;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/19/2019
 */
public class AbstractNativeQueryRepo {

    protected Query setParams(Query query, Map<String,Object> paramsMap){
        paramsMap.entrySet().stream().forEach(x->query.setParameter(x.getKey(),x.getValue()));
        return query;
    }
}
