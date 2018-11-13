package com.vens.data;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/11/12
 */
public class DynamicDataSourceHolder {
    /**
     * 使用static存在内存问题
     */
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String name) {
        holder.set(name);
    }
    public static String getDataSouce() {
        String dataSource=holder.get();
        return dataSource;
    }
}
