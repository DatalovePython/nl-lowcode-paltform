package com.newland.common.config;

import com.newland.common.util.SpringContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luzc
 * @date 2021/2/22 16:48
 * @desc
 */

@Configuration
public class CommonConfig {

    /**
     * Spring 上下文环境配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SpringContextHolder.class)
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
