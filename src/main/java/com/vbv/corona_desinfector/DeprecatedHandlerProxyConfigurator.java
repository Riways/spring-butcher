package com.vbv.corona_desinfector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;

public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {

	@Override
	public Object replaceWithProxyIfNeeded(Object t, Class implClass) {
		if (implClass.isAnnotationPresent(Deprecated.class)) {

			if (implClass.getInterfaces().length == 0) {
				return Enhancer.create(implClass, new net.sf.cglib.proxy.InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println( " ADDITIONAL PROXY LOGIC via net.sf.cglib.proxy.Enhancer;");
						return method.invoke(t,args);
					}
				});
			}

			return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(),
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							System.out.println( " ADDITIONAL PROXY LOGIC via java.lang.reflect.Proxy");
							return method.invoke(t,args);
						}
					});

		}
		return t;
	}

}
