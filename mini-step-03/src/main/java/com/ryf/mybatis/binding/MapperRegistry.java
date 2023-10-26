package com.ryf.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import com.google.common.collect.Maps;
import com.ryf.mybatis.session.Configuration;
import com.ryf.mybatis.session.SqlSession;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description 映射器注册机
 * @date 2023/7/11
 */
public class MapperRegistry {

    private Configuration config;

    /**
     * 将已添加的映射器代理加入到 HashMap
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = Maps.newHashMap();

    public MapperRegistry(Configuration config) {
        this.config = config;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (Objects.isNull(mapperProxyFactory)) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        return mapperProxyFactory.newInstance(sqlSession);
    }

    public <T> void addMapper(Class<T> type) {
        // mapper必须是接口才注册
        if (type.isInterface()) {
            if (knownMappers.containsKey(type)) {
                // 如果重复添加了，报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

    public boolean hasMapper(Class<?> type) {
        return knownMappers.containsKey(type);
    }
}
