package com.ryf.mybatis.binding;

import com.ryf.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

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

    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface, sqlSession);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
