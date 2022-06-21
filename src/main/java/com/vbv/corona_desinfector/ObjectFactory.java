package com.vbv.corona_desinfector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.SneakyThrows;

public class ObjectFactory {

	private static ObjectFactory ourInstance = new ObjectFactory();
	private Config config;

	private ObjectFactory() {

		// hardcode imitation of config(Class/file/document)
		Map<Class, Class> configImitation = new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class));

		config = new JavaConfig("com.vbv", configImitation);
	}

	public static ObjectFactory getInstance() {
		return ourInstance;

	}

	@SneakyThrows
	public <T> T createObject(Class<T> type) {
		Class<? extends T> implClass = type;
		T t = null;
		if (type.isInterface()) {
			implClass = config.getImplClass(type);
		}
		t = implClass.getDeclaredConstructor().newInstance();

		Field[] fields = implClass.getDeclaredFields();
		for (Field field : fields) {
			InjectProperty annotation = field.getAnnotation(InjectProperty.class);
			String pathToProperties = ClassLoader.getSystemClassLoader().getResource("application.properties")
					.getPath();
			Map<String, String> propertiesMap;
			String value;
			try (Stream<String> lines = new BufferedReader(new FileReader(pathToProperties)).lines()) {
				propertiesMap = lines.map(line -> line.split("="))
						.collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));

			}
			if (annotation != null) {

				if (annotation.value().isEmpty()) {
					value = propertiesMap.get(field.getName());

				} else {
					value = propertiesMap.get(annotation.value());
				}
				field.setAccessible(true);

				// in the first argument (t) we pass the object whose field we want to change
				field.set(t, value);
			}

		}

		return t;
	}
}
