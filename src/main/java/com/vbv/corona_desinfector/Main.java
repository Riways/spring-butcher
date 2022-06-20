package com.vbv.corona_desinfector;

public class Main {

	public static void main(String[] args) {
		CoronaDesinfector cd = new CoronaDesinfector();
		cd.start(new Room());
	}
}
