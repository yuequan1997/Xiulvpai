package org.yuequan.xiulvpai.security.authorization.permission.cache.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author yuequan
 * @since 1.0
 */
public class AuthorizationRefreshEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AuthorizationRefreshEvent(Object source) {
        super(source);
    }
}
