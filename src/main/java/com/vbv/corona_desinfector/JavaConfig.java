package com.vbv.corona_desinfector;

import java.util.Set;

import org.reflections.Reflections;

public class JavaConfig implements Config {

	private Reflections scanner;

	public JavaConfig(String packageToScan) {
		this.scanner = new Reflections(packageToScan);
	}

	public <T> Class<? extends T> getImplClass(Class<T> ifc) {
		Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
		if (classes.size() != 1) {
			throw new RuntimeException(ifc + " has 0 or more then one impl");
		}
		return classes.iterator().next();
	}

}
