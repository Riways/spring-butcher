package com.vbv.corona_desinfector;

public class ConsoleAnnouncer implements Announcer {

	public void firstAnnounce() {
		System.out.println("Annoncment: We urge everyone to leave the room");
	}

	public void secondAnnounce() {
		System.out.println("Annoncment: People can go back to the room");
	}

}
