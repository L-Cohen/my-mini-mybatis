package com.ryf.mybatis.mapping;

import com.ryf.mybatis.session.Configuration;

import java.util.Map;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description 映射的语句
 * <insert id="addUser" parameterType="User">
*         INSERT INTO `user`
*         (`name`,`email`,`age`,`sex`,`schoolName`)
*         VALUES
*         (#{name},#{email},#{age},#{sex},#{schoolName})
 *  </insert>
 * @date 2023/7/12
 */
public class MappedStatement {
    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;

    private String parameterType;
    private String resultType;
    private String sql;
    private Map<Integer, String> parameter;

    /**
     * MappedStatement建造者
     */
    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, SqlCommandType sqlCommandType, String msId, String parameterType, String resultType, String sql, Map<Integer, String> parameter) {
            mappedStatement.configuration = configuration;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.id = msId;
            mappedStatement.parameterType = parameterType;
            mappedStatement.resultType = resultType;
            mappedStatement.sql = sql;
            mappedStatement.parameter = parameter;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
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
