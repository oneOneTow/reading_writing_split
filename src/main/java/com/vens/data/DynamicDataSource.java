package com.vens.data;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/11/12
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder .getDataSouce();
    }
}
