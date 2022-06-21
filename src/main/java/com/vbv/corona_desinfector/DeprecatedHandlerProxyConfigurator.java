package com.vbv.corona_desinfector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {

	@Override
	public Object replaceWithProxyIfNeeded(Object t, Class implClass) {
		if (implClass.isAnnotationPresent(Deprecated.class)) {
			return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(),
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							System.out.println(this.getClass() + " ADDITIONAL PROXY LOGIC");
							return method.invoke(t);
						}
					});
		}
		return t;
	}

}
