package com.ryf.mybatis.session;

import com.ryf.mybatis.builder.xml.XMLConfigBuilder;

import java.io.Reader;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description
 * @date 2023/7/12
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    private SqlSessionFactory build(Configuration parse) {
        return null;
    }
}
