package com.vbv.corona_desinfector;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<Class, Class> configImitation = new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class));
		ApplicationContext context = Application.run("com.vbv", configImitation);
		context.getObject(CoronaDesinfector.class).start(new Room());
		
	}
}
