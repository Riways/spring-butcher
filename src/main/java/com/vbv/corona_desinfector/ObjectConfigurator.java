package com.vbv.corona_desinfector;


//Analog of Bean_post_processor from Spring
public interface ObjectConfigurator {
	

	void configure(Object t, ApplicationContext context);

}
