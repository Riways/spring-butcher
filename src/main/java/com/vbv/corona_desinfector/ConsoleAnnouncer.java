package com.vbv.corona_desinfector;

public class ConsoleAnnouncer implements Announcer {
	
	@InjectByType
	private Recommender recommender;

	public void firstAnnounce() {
		System.out.println("Annoncment: We urge everyone to leave the room");
		recommender.recommend();
	}

	public void secondAnnounce() {
		System.out.println("Annoncment: People can go back to the room");
	}

}
