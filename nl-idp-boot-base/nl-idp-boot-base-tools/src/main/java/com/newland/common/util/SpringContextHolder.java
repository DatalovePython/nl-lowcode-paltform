package com.newland.common.util;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author luzc
 * @date 2021/2/22 16:49
 * @desc 以静态变量保存spring ApplicationContext 便于其他地方调用
 */

@Slf4j
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * @return 取得存储在静态变量中的ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @param applicationContext
     * @throws BeansException 实现ApplicationContextAware接口的context注入函数，将其存入静态变量
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        checkApplicationContext();
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * @param name
     * @param <T>
     * @return 从静态变量ApplicationContext中取得bean，自动转型为所赋值对象的类型
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    // 从静态变量ApplicationContext中取得bean，自动转型为所赋值对象的类型
    public static <T> T getHandler(String name, Class<T> cls) {
        T t = null;
        if (ObjectUtil.isNotEmpty(name)) {
            checkApplicationContext();
            try {
                t = applicationContext.getBean(name, cls);
            } catch (Exception e) {
                log.error("*******" + name + "未定义");
            }
        }
        return t;
    }

    // 从静态变量ApplicationContext中取得bean，自动转型为所赋值对象的类型
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    // 清楚applicationContext 静态变量
    public static void cleanApplicationContext() {
        applicationContext = null;
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext 未注入，请在applicationContext.xml中定义springContextHolder");
        }
    }
}
