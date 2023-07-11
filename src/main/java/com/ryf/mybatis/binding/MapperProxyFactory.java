package com.ryf.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description 映射器代理工厂
 * @date 2023/7/11
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;


    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface, sqlSession);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
