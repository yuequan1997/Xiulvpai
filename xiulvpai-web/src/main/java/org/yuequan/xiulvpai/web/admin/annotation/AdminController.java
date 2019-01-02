package org.yuequan.xiulvpai.web.admin.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * A convenience annotation that is itself annotated with
 * {@link Controller} and {@link RequestMapping}
 * @author yuequan
 * @since
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@RequestMapping
public @interface AdminController {
    @AliasFor(annotation = Controller.class)
    String value() default "";
    @AliasFor(annotation = RequestMapping.class, value = "value")
    String path() default "/admin";
}
