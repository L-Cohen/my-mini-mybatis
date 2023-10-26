package com.ryf.mybatis.mapping;

import cn.hutool.db.meta.JdbcType;
import com.ryf.mybatis.session.Configuration;

/**
 * #｛ id, javaType= int, jdbcType=NUMERIC, typeHandler=DemoTypeHandler ｝
 */
public class ParameterMapping {
    private Configuration configuration;
    // property
    private String property;
    // javaType = int
    private Class<?> javaType = Object.class;
    // jdbcType=NUMERIC
    private JdbcType jdbcType;

    private ParameterMapping() {
    }

    public static class Builder {
        private ParameterMapping parameterMapping = new ParameterMapping();
        private Configuration configuration;
        // property
        private String property;
        // javaType = int
        private Class<?> javaType = Object.class;
        // jdbcType=NUMERIC
        private JdbcType jdbcType;

        public Builder(Configuration configuration, String property) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
        }

        public Builder javaType(Class<?> javaType) {
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }

        public ParameterMapping build() {
            return parameterMapping;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getProperty() {
        return property;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }
}
