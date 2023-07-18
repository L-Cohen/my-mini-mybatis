package com.ryf.mybatis.session;

import com.google.common.collect.Maps;
import com.ryf.mybatis.binding.MapperRegistry;
import com.ryf.mybatis.mapping.MappedStatement;

import java.util.Map;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description 配置类
 * @date 2023/7/12
 */
public class Configuration {
    /**
     * 映射注册机
     */
    private MapperRegistry mapperRegistry = new MapperRegistry(this);
    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatement = Maps.newHashMap();
    public void addMappedStatement(MappedStatement statement) {
        mappedStatement.put(statement.getId(), statement);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatement.get(id);
    }

    /**
     * 根据包名注册mapper
     * @param packageName
     */
    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }
}
