package org.yuequan.xiulvpai.web.support.thymeleaf.dialect.expression.table;

import org.springframework.security.util.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author yuequan
 * @since
 **/
public class Table {
    private final Locale locale;

    public Table(final Locale locale) {
        super();
        this.locale = locale;
    }

    public List<String> toI18NModelAttrPrefixes(String model, String attr){
        var attrs = attr.split(",");
        var i18nMessageAddedPrefixes = new ArrayList<String>(attrs.length);
        for (String noPrefixAttr : attrs) {
            StringBuilder builder = new StringBuilder("model.");
            builder.append(model);
            builder.append(".");
            builder.append("attr");
            builder.append(".");
            builder.append(noPrefixAttr);
            i18nMessageAddedPrefixes.add(builder.toString());
        }
        return i18nMessageAddedPrefixes;
    }

    public List<Object> getValue(Object object, String attrNames) throws NoSuchFieldException, IllegalAccessException {
        var values = new ArrayList<Object>();
        var fieldNames = attrNames.split(",");
        for (String fieldName : fieldNames) {
            values.add(getFieldValue(object, fieldName));
        }
        return values;
    }

    private Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
