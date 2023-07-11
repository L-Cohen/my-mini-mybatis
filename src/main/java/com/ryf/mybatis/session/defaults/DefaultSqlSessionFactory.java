package com.ryf.mybatis.session.defaults;

import com.ryf.mybatis.binding.MapperRegistry;
import com.ryf.mybatis.session.SqlSession;
import com.ryf.mybatis.session.SqlSessionFactory;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description
 * @date 2023/7/11
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
