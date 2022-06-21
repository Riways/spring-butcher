package com.vbv.corona_desinfector;

public class Main {

	public static void main(String[] args) {

		// for proper use of inversion of control we should not create instances of
		// singletone objects
		// (services, repositories controllers) by operator "new"
		CoronaDesinfector cd = ObjectFactory.getInstance().createObject(CoronaDesinfector.class);

		cd.start(new Room());
	}
}
