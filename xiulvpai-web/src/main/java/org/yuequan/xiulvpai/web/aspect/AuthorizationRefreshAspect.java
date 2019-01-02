package org.yuequan.xiulvpai.web.aspect;

import org.yuequan.xiulvpai.security.authorization.permission.cache.event.AuthorizationRefreshEvent;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 鉴权刷新切面配置
 * @author yuequan
 * @since 1.0
 */
@Aspect
@Component
public class AuthorizationRefreshAspect implements ApplicationContextAware, Ordered {

    private ApplicationContext applicationContext;

    @Pointcut("execution(* org.yuequan.xiulvpai.web.service.RoleService.save(..))")
    public void writeRolePointcut(){

    }

    @After("writeRolePointcut()")
    public void publish() {
        applicationContext.publishEvent(new AuthorizationRefreshEvent(this));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
