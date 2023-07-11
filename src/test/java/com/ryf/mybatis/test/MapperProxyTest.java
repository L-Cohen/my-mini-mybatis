package com.ryf.mybatis.test;

import cn.hutool.log.Log;
import com.ryf.mybatis.binding.MapperRegistry;
import com.ryf.mybatis.session.SqlSession;
import com.ryf.mybatis.session.SqlSessionFactory;
import com.ryf.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.ryf.mybatis.test.dao.IUserDao;
import org.junit.Test;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description
 * @date 2023/7/11
 */
public class MapperProxyTest {

    private static final Log logger = Log.get();

    /**
     * step-1 test
     */
    // @Test
    // public void testMapper() {
    //     MapperProxyFactory<IUserDao> proxyFactory = new MapperProxyFactory<>(IUserDao.class);
    //     Map<String, String> sqlSession = Maps.newHashMap();
    //     sqlSession.put("com.ryf.mybatis.test.dao.IUserDao.getUserId", "模拟查询");
    //     IUserDao userDao = proxyFactory.newInstance(sqlSession);
    //     String userId = userDao.getUserId("1");
    //     logger.info("result is :{}", userId);
    // }

    @Test
    public void testMapper2() {
        // 1. 注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.ryf.mybatis.test.dao");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory factory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = factory.openSession();

        // 3. 获取映射器对象
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        String result = mapper.getUserId("1111");
        logger.info("测试结果：{}", result);
    }
}
