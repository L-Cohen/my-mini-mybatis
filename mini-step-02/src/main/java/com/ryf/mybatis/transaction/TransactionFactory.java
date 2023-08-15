package com.ryf.mybatis.transaction;

import com.ryf.mybatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description 事务工厂接口定义
 * @date 2023/8/15
 */
public interface TransactionFactory {

    /**
     * 从给定的连接中获取一个事务
     *
     * @param conn 给定的连接
     * @return 获取的事务对象
     */
    Transaction newTransaction(Connection conn);

    /**
     * 从给定的数据源中获取事务，并对事务进行一些配置
     * @param dataSource 数据源
     * @param level 数据隔离级别
     * @param autoCommit 是否自动提交事务
     * @return 获取的事务对象
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);
}
