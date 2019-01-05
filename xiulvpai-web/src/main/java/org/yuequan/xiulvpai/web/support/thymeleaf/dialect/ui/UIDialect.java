package org.yuequan.xiulvpai.web.support.thymeleaf.dialect.ui;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.yuequan.xiulvpai.web.support.thymeleaf.dialect.ui.processor.UIAppendActiveClassProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * UI Dialect
 * @author yuequan
 * @since 1.0
 **/
public class UIDialect extends AbstractProcessorDialect {
    private static final String DIALECT_NAME = "UI Dialect";

    public UIDialect() {
        // We will set this dialect the same "dialect processor" precedence as
        // the Standard Dialect, so that processor executions can interleave.
        super(DIALECT_NAME, "ui", StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new UIAppendActiveClassProcessor(dialectPrefix));
        return processors;
    }
}
