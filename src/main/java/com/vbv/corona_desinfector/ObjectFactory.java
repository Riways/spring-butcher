package com.vbv.corona_desinfector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.Setter;
import lombok.SneakyThrows;

public class ObjectFactory {

	@Setter
	private static ObjectFactory ourInstance;
	private ApplicationContext context;
	private List<ObjectConfigurator> configurators = new ArrayList<ObjectConfigurator>();;
	

	@SneakyThrows
	public  ObjectFactory(ApplicationContext context) {
		this.context = context;
		
		// if any new ObjectConfigurator classes will be created, they automatically would be added to configurators list   
		Set<Class<? extends ObjectConfigurator>> objectConfiguratorClasses = context.getConfig().getScanner()
				.getSubTypesOf(ObjectConfigurator.class);
		for (Class<? extends ObjectConfigurator> clazz : objectConfiguratorClasses) {
			configurators.add(clazz.getDeclaredConstructor().newInstance());
		}

	}

	
	public static ObjectFactory getInstance() {
		return ourInstance;

	}

	@SneakyThrows
	public <T> T createObject(Class<T> implClass) {

		T t = implClass.getDeclaredConstructor().newInstance();
		
		//lot of work but it executes only on bootstrap if our objects are Singletone
		configurators.forEach((objectConfigurator) -> objectConfigurator.configure(t, context));

		return t;
	}
}
