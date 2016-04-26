package extensible.myxml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class WqfNameSpaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("dateformat", new SimpleDateFormatBeanDefinitionParser());
	}

}
