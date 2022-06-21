package com.vbv.corona_desinfector;

import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import lombok.Getter;

public class JavaConfig implements Config {

	@Getter
	private Reflections scanner;
	
	private Map<Class, Class> ifc2ImplClass;

	public JavaConfig(String packageToScan, Map<Class, Class> ifc2ImplClass) {
		this.ifc2ImplClass = ifc2ImplClass;
		this.scanner = new Reflections(packageToScan);
	}

	public <T> Class<? extends T> getImplClass(Class<T> ifc) {
		Class computeIfAbsent = ifc2ImplClass.computeIfAbsent(ifc, aClass -> {
			Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
			if (classes.size() != 1) {
				throw new RuntimeException(ifc + " has 0 or more then one impl");
			}
		
			Class clazz = classes.iterator().next();
			return clazz;
		});
		return computeIfAbsent;
	}

}
