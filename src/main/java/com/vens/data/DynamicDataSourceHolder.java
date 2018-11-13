package com.vens.data;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/11/12
 */
public class DynamicDataSourceHolder {
    /**
     * ʹ��static�����ڴ�����
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
