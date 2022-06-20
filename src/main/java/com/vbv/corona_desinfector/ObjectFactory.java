package com.vbv.corona_desinfector;


import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;

public class ObjectFactory {

	private static ObjectFactory ourInstance = new ObjectFactory();
	private Config config ;

	private ObjectFactory() {
		//hardcode
		config = new JavaConfig("com.vbv", new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class)));
	}
	
	public static ObjectFactory getInstance() {
		return ourInstance;

	}

	
	@SneakyThrows
	public <T> T createObject(Class<T> type) {
		Class<? extends T> implClass = type;
		T t = null;
		if (type.isInterface()) {
			implClass = config.getImplClass(type);
		}
		t = implClass.getDeclaredConstructor().newInstance();
		return t;
	}
}
