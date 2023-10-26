package com.ryf.mybatis.test.dao;

import com.ryf.mybatis.test.to.User;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description
 * @date 2023/7/11
 */
public interface IUserDao {

    String queryUserInfoByIdStr(String id);
    User queryUserInfoById(String id);
}
