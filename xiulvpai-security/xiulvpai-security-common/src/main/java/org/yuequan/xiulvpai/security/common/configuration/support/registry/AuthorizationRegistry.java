package org.yuequan.xiulvpai.security.common.configuration.support.registry;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 公用的授权扩展接口
 * @see ExpressionUrlAuthorizationConfigurer
 * @author yuequan
 * @since
 **/
public interface AuthorizationRegistry {
    void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests);
}
