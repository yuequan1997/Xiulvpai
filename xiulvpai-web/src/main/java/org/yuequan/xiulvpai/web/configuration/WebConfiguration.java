package org.yuequan.xiulvpai.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yuequan.xiulvpai.web.support.thymeleaf.dialect.expression.table.TableDialect;
import org.yuequan.xiulvpai.web.support.thymeleaf.dialect.ui.UIDialect;

/**
 * @author yuequan
 * @since 1.0
 **/
@Configuration
public class WebConfiguration {
    @Bean
    public UIDialect uiDialect(){
        return new UIDialect();
    }

    @Bean
    public TableDialect tableDialect(){
        return new TableDialect();
    }
}
