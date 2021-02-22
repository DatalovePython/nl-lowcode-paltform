package com.newland.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author luzc
 * @date 2021/2/22 16:00
 * @desc 消息队列初始化注解
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RabbitComponent {

    @AliasFor(
            annotation = Component.class
    )
    String value();

}
