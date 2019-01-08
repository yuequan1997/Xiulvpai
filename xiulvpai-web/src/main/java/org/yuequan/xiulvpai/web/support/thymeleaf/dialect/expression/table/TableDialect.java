package org.yuequan.xiulvpai.web.support.thymeleaf.dialect.expression.table;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

/**
 * @author yuequan
 * @since 1.0
 **/
public class TableDialect extends AbstractDialect implements IExpressionObjectDialect {

    private final TableExpressionFactory TABLE_EXPRESSION_OBJECTS_FACTORY = new TableExpressionFactory();

    public TableDialect() {
        super("table");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return TABLE_EXPRESSION_OBJECTS_FACTORY;
    }
}
