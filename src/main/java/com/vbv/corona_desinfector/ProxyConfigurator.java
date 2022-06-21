package com.vbv.corona_desinfector;

public interface ProxyConfigurator{
	Object replaceWithProxyIfNeeded(Object t, Class implClass);
}
