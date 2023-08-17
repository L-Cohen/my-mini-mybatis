package com.ryf.mybatis.mapping;

import java.util.Map;

public class BoundSql {
    private String parameterType;
    private String resultType;
    private String sql;
    private Map<Integer, String> parameter;

    public BoundSql(String parameterType, String resultType, String sql, Map<Integer, String> parameter) {
        this.parameterType = parameterType;
        this.resultType = resultType;
        this.sql = sql;
        this.parameter = parameter;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<Integer, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<Integer, String> parameter) {
        this.parameter = parameter;
    }
}
