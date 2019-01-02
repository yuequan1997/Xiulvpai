package org.yuequan.xiulvpai.security.authorization.permission.cache.listener;

import org.yuequan.xiulvpai.security.authorization.permission.AuthorizationDecisionMaker;
import org.yuequan.xiulvpai.security.authorization.permission.cache.event.AuthorizationRefreshEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SmartApplicationListener;

/**
 * Authorization配置的刷新监听
 * @author yuequan
 * @since 1.0
 */
@Configuration
public class AuthorizationRefreshListener implements SmartApplicationListener {

    @Autowired
    private AuthorizationDecisionMaker decisionMaker;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType.isAssignableFrom(AuthorizationRefreshEvent.class);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof AuthorizationRefreshEvent){
            try {
                refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void refresh() throws Exception {
        decisionMaker.refreshCache();
    }
}
