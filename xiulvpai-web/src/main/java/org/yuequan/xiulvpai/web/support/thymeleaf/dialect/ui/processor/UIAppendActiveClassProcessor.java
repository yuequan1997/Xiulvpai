package org.yuequan.xiulvpai.web.support.thymeleaf.dialect.ui.processor;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * 追加active样式，根据当前url和传入url进行匹配
 * @author yuequan
 * @since 1.0
 **/
public class UIAppendActiveClassProcessor extends AbstractAttributeTagProcessor {

    private static final String ATTR_NAME = "active";
    private static final int PRECEDENCE = 10000;


    public UIAppendActiveClassProcessor(final String dialectPrefix){
        super(
                TemplateMode.HTML,
                dialectPrefix,
                null,
                false,
                ATTR_NAME,
                true,
                PRECEDENCE,
                true);
    }


    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler structureHandler) {
        final var configuration = context.getConfiguration();
        final var parser = StandardExpressions.getExpressionParser(configuration);

        var attributeValues = attributeValue.split(",");
        attributeValue = attributeValues[0];
        boolean isPrefixMatch = attributeValues.length == 1;
        final var expression = parser.parseExpression(context, attributeValue);
        var url = (String) expression.execute(context);
        if(context instanceof WebEngineContext){
            var webEngineContext = ((WebEngineContext) context);
            var requestUrl = webEngineContext.getRequest().getServletPath();
            if(url.endsWith("/")){
                url = url.substring(0, url.length() - 1);
            }

            if(requestUrl.endsWith("/")){
                requestUrl = requestUrl.substring(0, requestUrl.length() - 1);
            }

            if(url.equals(requestUrl)){
                addActiveClass(tag, structureHandler);
            }else{
                if(isPrefixMatch){
                    url = url + "/";
                    if(requestUrl.startsWith(url)){
                        addActiveClass(tag, structureHandler);
                    }
                }
            }
        }
    }

    private void addActiveClass(IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {
        var eClassAttribute = tag.getAttribute("class");
        final String originClass;
        if(eClassAttribute == null){
            originClass = "";
        }else{
            originClass = eClassAttribute.getValue();
        }
        structureHandler.setAttribute("class", originClass + " active");
    }


}
