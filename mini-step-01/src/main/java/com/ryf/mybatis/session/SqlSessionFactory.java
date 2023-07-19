package com.ryf.mybatis.session;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description
 * @date 2023/7/11
 */
public interface SqlSessionFactory {

    /**
     * 打开一个 session
     * @return SqlSession
     */
    SqlSession openSession();
}
