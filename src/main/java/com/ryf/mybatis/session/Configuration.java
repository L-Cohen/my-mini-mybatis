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
    private MapperRegistry mapperRegistry;
    protected final Map<String, MappedStatement> mappedStatement = Maps.newHashMap();

    public void addMappedStatement(MappedStatement statement) {
        mappedStatement.put(statement.getId(), statement);
    }

    public<T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }
}
