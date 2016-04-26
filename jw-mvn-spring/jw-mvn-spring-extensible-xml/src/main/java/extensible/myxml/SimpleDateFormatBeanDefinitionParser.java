package extensible.myxml;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class SimpleDateFormatBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String pattern = element.getAttribute("pattern");
		builder.addConstructorArgReference(pattern);  
		
		String lenient = element.getAttribute("lenient");  
        if (StringUtils.hasText(lenient)) {  
        	builder.addPropertyValue("lenient", Boolean.valueOf(lenient));  
        }  
	}

	@Override
	protected Class<SimpleDateFormat> getBeanClass(Element element) {
		return SimpleDateFormat.class;   
	}

	
}
