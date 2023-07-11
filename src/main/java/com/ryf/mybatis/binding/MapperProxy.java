package com.ryf.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description 映射器代理
 * @date 2023/7/11
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final Long serialVersionUID = -8737713205817139458L;
    private final Class<T> mapperInterface;

    private Map<String, String> sqlSession;

    public MapperProxy(Class<T> mapperInterface, Map<String, String> sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getName())) {
            return method.invoke(proxy, args);
        } else {
            return "你被代理了" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }
}
