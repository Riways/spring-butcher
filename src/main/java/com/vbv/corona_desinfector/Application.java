package com.vbv.corona_desinfector;

import java.util.Map;

//@SpringBootApplication analog
public class Application {
	
	public static ApplicationContext run(String packageToScan, Map<Class,Class> interface2ImplClass) {
		JavaConfig config = new JavaConfig(packageToScan, interface2ImplClass);
		ApplicationContext context = new  ApplicationContext(config);
		ObjectFactory objectFactory = new ObjectFactory(context);
		//TODO homework - init all singletons which are not lazy
		context.setFactory(objectFactory);
		return context;
	}

}
