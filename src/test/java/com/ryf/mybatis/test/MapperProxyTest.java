package com.ryf.mybatis.test;

import cn.hutool.log.Log;
import com.google.common.collect.Maps;
import com.ryf.mybatis.binding.MapperProxyFactory;
import com.ryf.mybatis.test.dao.IUserDao;
import org.junit.Test;

import java.util.Map;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description
 * @date 2023/7/11
 */
public class MapperProxyTest {

    private static final Log logger = Log.get();

    @Test
    public void testMapper() {
        MapperProxyFactory<IUserDao> proxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = Maps.newHashMap();
        sqlSession.put("com.ryf.mybatis.test.dao.IUserDao.getUserId", "模拟查询");
        IUserDao userDao = proxyFactory.newInstance(sqlSession);
        String userId = userDao.getUserId("1");
        logger.info("result is :{}", userId);
    }
}
