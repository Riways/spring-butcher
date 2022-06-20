package com.vbv.corona_desinfector;


import lombok.SneakyThrows;

public class ObjectFactory {

	private static ObjectFactory ourInstance = new ObjectFactory();
	private Config config = new JavaConfig("com.vbv");

	public static ObjectFactory getInstance() {
		return ourInstance;

	}

	private ObjectFactory() {

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
