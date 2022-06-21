package com.vbv.corona_desinfector;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Same class as in Spring
public class ApplicationContext {
	private ObjectFactory factory;
	private Map<Class, Object> cache = new ConcurrentHashMap<>();
	
	public <T> T getObject(Class<T> type) {
		return null;
	}

}
