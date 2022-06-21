package com.vbv.corona_desinfector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.SneakyThrows;

public class ObjectFactory {

	private static ObjectFactory ourInstance = new ObjectFactory();
	private List<ObjectConfigurator> configurators = new ArrayList<ObjectConfigurator>();;
	private Config config;

	@SneakyThrows
	private ObjectFactory() {

		// hardcode imitation of configuration(Class/file/document)
		Map<Class, Class> configImitation = new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class));
		config = new JavaConfig("com.vbv", configImitation);
		
		// if any new ObjectConfigurator classes will be created, they automatically would be added to configurators list   
		Set<Class<? extends ObjectConfigurator>> objectConfiguratorClasses = config.getScanner()
				.getSubTypesOf(ObjectConfigurator.class);
		for (Class<? extends ObjectConfigurator> clazz : objectConfiguratorClasses) {
			configurators.add(clazz.getDeclaredConstructor().newInstance());
		}

	}

	public static ObjectFactory getInstance() {
		return ourInstance;

	}

	@SneakyThrows
	public <T> T createObject(Class<T> type) {
		Class<? extends T> implClass = type;
		if (type.isInterface()) {
			implClass = config.getImplClass(type);
		}
		T t = implClass.getDeclaredConstructor().newInstance();
		
		//lot of work but it executes only on bootstrap if our objects are Singletone
		configurators.forEach((objectConfigurator) -> objectConfigurator.configure(t));

		return t;
	}
}
