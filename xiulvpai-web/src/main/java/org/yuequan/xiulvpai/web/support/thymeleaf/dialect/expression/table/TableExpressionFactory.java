package org.yuequan.xiulvpai.web.support.thymeleaf.dialect.expression.table;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.yuequan.xiulvpai.web.support.thymeleaf.dialect.expression.table.Table;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yuequan
 * @since
 **/
public class TableExpressionFactory implements IExpressionObjectFactory {

    private static final String TABLE_EVALUATION_VARIABLE_NAME = "table";

    private static final Set<String> ALL_EXPRESSION_OBJECT_NAMES = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(TABLE_EVALUATION_VARIABLE_NAME)));
    @Override
    public Set<String> getAllExpressionObjectNames() {
        return ALL_EXPRESSION_OBJECT_NAMES;
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        if (TABLE_EVALUATION_VARIABLE_NAME.equals(expressionObjectName)) {
            return new Table(context.getLocale());
        }
        return null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return expressionObjectName != null && TABLE_EVALUATION_VARIABLE_NAME.equals(expressionObjectName);
    }
}
