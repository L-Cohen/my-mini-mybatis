package com.ryf.mybatis.builder;

import com.ryf.mybatis.session.Configuration;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description
 * @date 2023/7/12
 */
public class BaseBuilder {
    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
