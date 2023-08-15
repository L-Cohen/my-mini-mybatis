package com.ryf.mybatis.transaction.jdbc;

import com.ryf.mybatis.session.TransactionIsolationLevel;
import com.ryf.mybatis.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description jdbc-事务，封装了获取链接、提交事务等操作
 * @date 2023/8/15
 */
public class JdbcTransaction implements Transaction {

    // 数据库连接
    protected Connection connection;
    // 数据源
    protected DataSource dataSource;
    // 事务隔离级别
    protected TransactionIsolationLevel level = TransactionIsolationLevel.NONE;
    // 是否自动提交事务
    protected boolean autoCommit;

    public JdbcTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        this.dataSource = dataSource;
        this.level = level;
        this.autoCommit = autoCommit;
    }

    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        connection = dataSource.getConnection();
        connection.setAutoCommit(autoCommit);
        connection.setTransactionIsolation(level.getLevel());
        return connection;
    }

    @Override
    public void commit() throws SQLException {
        if (Objects.nonNull(connection) && !connection.getAutoCommit()) {
            connection.commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (Objects.nonNull(connection) && !connection.getAutoCommit()) {
            connection.rollback();
        }
    }

    @Override
    public void close() throws SQLException {
        if (Objects.nonNull(connection)) {
            connection.close();
        }
    }
}
