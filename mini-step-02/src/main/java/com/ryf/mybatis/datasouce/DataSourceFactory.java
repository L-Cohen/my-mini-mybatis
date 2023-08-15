package com.ryf.mybatis.datasouce;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description
 * @date 2023/8/16
 */
public interface DataSourceFactory {
    void setProperties(Properties props);

    DataSource getDataSource();
}
