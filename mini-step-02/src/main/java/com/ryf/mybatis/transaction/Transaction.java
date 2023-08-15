package com.ryf.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description
 * @date 2023/8/15
 */
public interface Transaction {

    /**
     * 获取数据库连接
     *
     * @return Connection
     */
    Connection getConnection() throws SQLException;

    /**
     * 提交事务
     */
    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;
}
