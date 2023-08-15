package com.ryf.mybatis.session;

import com.alibaba.druid.support.ibatis.DruidDataSourceFactory;
import com.google.common.collect.Maps;
import com.ryf.mybatis.binding.MapperRegistry;
import com.ryf.mybatis.mapping.Environment;
import com.ryf.mybatis.mapping.MappedStatement;
import com.ryf.mybatis.transaction.jdbc.JdbcTransactionFactory;
import com.ryf.mybatis.type.TypeAliasRegistry;

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
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);
    protected Environment environment;
    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatement = Maps.newHashMap();

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
    }
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

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public void setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Map<String, MappedStatement> getMappedStatement() {
        return mappedStatement;
    }
}
