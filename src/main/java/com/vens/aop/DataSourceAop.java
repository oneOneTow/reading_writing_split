package com.vens.aop;

import com.vens.annotion.DataSource;
import com.vens.data.DynamicDataSourceHolder;
import com.vens.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/11/12
 */
public class DataSourceAop {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);
    public void before(JoinPoint pjp){
        Object target=pjp.getTarget();
        String method=pjp.getSignature().getName();
        Class<?> [] clazzs=target.getClass().getInterfaces();
        Class<?> [] parameterTypes =((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes();
        try {
            Method m=clazzs[0].getMethod(method,parameterTypes);
            if(m!=null&&m.isAnnotationPresent(DataSource.class)){
                DataSource dataSource=m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSource(dataSource.value());
                logger.info("dataSource:{}",dataSource.value());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
