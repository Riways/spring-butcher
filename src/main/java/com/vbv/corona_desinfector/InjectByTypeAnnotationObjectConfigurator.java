package com.vbv.corona_desinfector;

import java.lang.reflect.Field;

import lombok.SneakyThrows;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

	@Override
	@SneakyThrows
	public void configure(Object t) {
		for (Field field : t.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(InjectByType.class)) {
				field.setAccessible(true);
				Class clazz = field.getType();
				Object obj = ObjectFactory.getInstance().createObject(clazz);
				field.set(t, obj);
				
			}
		}
	}

}
