package com.ryf.mybatis.session.defaults;

import com.ryf.mybatis.session.Configuration;
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

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
