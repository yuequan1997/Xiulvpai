package org.yuequan.xiulvpai.web.support.thymeleaf.dialect.expression.table;

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

    public List<Object> getValue(Object object, String attrNames){
        var values = new ArrayList<Object>();
        return values;
    }
}
