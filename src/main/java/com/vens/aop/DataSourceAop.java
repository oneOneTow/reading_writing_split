package com.vens.aop;

import com.vens.annotion.DataSource;
import com.vens.data.DynamicDataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/11/12
 */
public class DataSourceAop {
    public void before(ProceedingJoinPoint pjp){
        Object target=pjp.getTarget();
        String method=pjp.getSignature().getName();
        Class<?> [] clazzs=target.getClass().getInterfaces();
        Class<?> [] parameterTypes =((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes();
        try {
            Method m=clazzs[0].getMethod(method,parameterTypes);
            if(m!=null&&m.isAnnotationPresent(DataSource.class)){
                DataSource dataSource=m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSource(dataSource.value());
                System.out.println(dataSource.value());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
