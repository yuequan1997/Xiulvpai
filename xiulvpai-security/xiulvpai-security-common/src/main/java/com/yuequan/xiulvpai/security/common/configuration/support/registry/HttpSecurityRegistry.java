package com.yuequan.xiulvpai.security.common.configuration.support.registry;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * {@link HttpSecurity} 配置
 * @author yuequan
 * @since 1.0.0
 **/
public interface HttpSecurityRegistry {
    void configure(HttpSecurity http);
}
