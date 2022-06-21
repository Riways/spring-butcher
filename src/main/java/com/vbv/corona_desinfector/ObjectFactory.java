package com.vbv.corona_desinfector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
	private List<ProxyConfigurator> configuratorsProxy = new ArrayList<ProxyConfigurator>();;

	@SneakyThrows
	public ObjectFactory(ApplicationContext context) {
		this.context = context;

		// if any new ObjectConfigurator classes will be created, they automatically
		// would be added to configurators list
		Set<Class<? extends ObjectConfigurator>> objectConfiguratorClasses = context.getConfig().getScanner()
				.getSubTypesOf(ObjectConfigurator.class);
		for (Class<? extends ObjectConfigurator> clazz : objectConfiguratorClasses) {
			configurators.add(clazz.getDeclaredConstructor().newInstance());
		}
		Set<Class<? extends ProxyConfigurator>> proxyConfiguratorClasses = context.getConfig().getScanner()
				.getSubTypesOf(ProxyConfigurator.class);
		for (Class<? extends ProxyConfigurator> clazz : proxyConfiguratorClasses) {
			configuratorsProxy.add(clazz.getDeclaredConstructor().newInstance());
		}

	}

	public static ObjectFactory getInstance() {
		return ourInstance;

	}

	@SneakyThrows
	public <T> T createObject(Class<T> implClass) {

		T t = implClass.getDeclaredConstructor().newInstance();

		configure(t);

		
		invokeInit(implClass, t);

		t = replaceWithProxy(implClass, t);
		
		return t;
	}

	private <T> T replaceWithProxy(Class<T> implClass, T t) {
		for(ProxyConfigurator pcfg: configuratorsProxy) {
			t=(T) pcfg.replaceWithProxyIfNeeded(t, implClass);
		}
		return t;
	}

	@SneakyThrows
	private <T> void invokeInit(Class<T> implClass, T t) {
		for (Method method : implClass.getMethods()) {
			if (method.isAnnotationPresent(PostConstruct.class)) {
				method.invoke(t);
			}
		}
	}

	private <T> void configure(T t  ) {
		// lot of work but it executes only on bootstrap if our objects are Singletone
		configurators.forEach((objectConfigurator) -> objectConfigurator.configure(t, context));
	}
}
