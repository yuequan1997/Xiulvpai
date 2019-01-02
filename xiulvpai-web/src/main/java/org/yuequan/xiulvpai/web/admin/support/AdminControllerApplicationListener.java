package org.yuequan.xiulvpai.web.admin.support;

import org.yuequan.xiulvpai.web.admin.annotation.AdminController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.data.util.AnnotatedTypeScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @see <a href="https://checkerframework.org/jsr308/specification/java-annotation-extensions.html"></a>
 * so hack
 * @author yuequan
 * @since 1.0
 **/
@Slf4j
public class AdminControllerApplicationListener implements SmartApplicationListener {

    private static final String PREFIX_PATH = "admin";

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return ApplicationEnvironmentPreparedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationEnvironmentPreparedEvent){
            onApplicationStartingEvent((ApplicationEnvironmentPreparedEvent) event);
        }
    }

    private void onApplicationStartingEvent(ApplicationEnvironmentPreparedEvent event) {
        var scanner = new AnnotatedTypeScanner(AdminController.class);
        var classes = scanner.findTypes("com.yuequan.xiulvpai");
        classes.forEach(clazz -> {
            var annotation = clazz.getAnnotation(AdminController.class);
            var path = annotation.path();
            if(!(path.startsWith("/"+PREFIX_PATH) || path.startsWith(PREFIX_PATH+"/"))){
                try {
                    String prefix;
                    if(path.startsWith("/")){
                        prefix = "/"+PREFIX_PATH;
                    }else{
                        prefix = "/"+PREFIX_PATH+"/";
                    }
                    var invocation =  Proxy.getInvocationHandler(annotation);
                    Field memberValuesField = invocation.getClass().getDeclaredField("memberValues");
                    memberValuesField.setAccessible(true);
                    Map<String, Object> memberValues = (Map<String, Object>) memberValuesField.get(invocation);
                    memberValues.put("path", prefix + path);
                    log.info( "admin controller mapping {} to {}", path, prefix + path);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
