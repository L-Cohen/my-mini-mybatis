package com.ryf.mybatis.session.defaults;

import com.ryf.mybatis.mapping.BoundSql;
import com.ryf.mybatis.mapping.Environment;
import com.ryf.mybatis.mapping.MappedStatement;
import com.ryf.mybatis.session.Configuration;
import com.ryf.mybatis.session.SqlSession;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description
 * @date 2023/7/11
 */
@Slf4j
public class DefaultSqlSession implements SqlSession {

    /**
     * mapper注册器
     */
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + "方法：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        log.info("你被代理了！ 方法：{} 入参：{} 待执行sql：{}", statement, parameter, mappedStatement.getBoundSql());
        Environment environment = configuration.getEnvironment();
        DataSource dataSource = environment.getDataSource();
        BoundSql boundSql = mappedStatement.getBoundSql();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql())) {
            preparedStatement.setInt(1, Integer.parseInt(((Object[])parameter)[0].toString()));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> objList = resultSet2Obj(resultSet, Class.forName(boundSql.getResultType()));
            return objList.get(0);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                T obj = (T) clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object result = resultSet.getObject(i);
                    java.lang.String columnName = metaData.getColumnName(i);
                    // 骚操作
                    java.lang.String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method;
                    if (result instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else {
                        method = clazz.getMethod(setMethod, result.getClass());
                    }
                    method.invoke(obj, result);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

}
