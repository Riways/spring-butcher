package com.vbv.corona_desinfector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InjectPropertyAnnotationConfigurator implements ObjectConfigurator {

	private Map<String, String> propertiesMap;

	public InjectPropertyAnnotationConfigurator() {

		String pathToProperties = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
		
		try (Stream<String> lines = new BufferedReader(new FileReader(pathToProperties)).lines()) {
			propertiesMap = lines.map(line -> line.split("=")).collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void configure(Object t) {
		Class<?> implClass = t.getClass();
		Field[] fields = implClass.getDeclaredFields();
		for (Field field : fields) {
			InjectProperty annotation = field.getAnnotation(InjectProperty.class);

			String value;

			if (annotation != null) {

				if (annotation.value().isEmpty()) {
					value = propertiesMap.get(field.getName());

				} else {
					value = propertiesMap.get(annotation.value());
				}

				field.setAccessible(true);

				try {
					// in the first argument (t) we pass the object whose field we want to change
					field.set(t, value);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}
