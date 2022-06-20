package com.vbv.corona_desinfector;


//7+ responsibilities in perspective
public class CoronaDesinfector {
	
	
	/* 3 responsibilities:
	 *		1. create ConsoleAnnouncer
	 *		2. choose Announcer implementation
	 *		3. initialize Announcer implementation ( constructor parameters, or getters-setters) 
		*/
	private Announcer announcer = new ConsoleAnnouncer();
	private Policemen policemen = new PolicementImpl();

	
	// 3 responsibilities:
	public void start(Room room) {
		announcer.firstAnnounce();
		policemen.policeWork();
		desinfectRoom(room);
		announcer.secondAnnounce();
	}
	
	
	//1(main) responsibility
	public void desinfectRoom(Room room) {
		System.out.println(room.roomPurpose + " desinfected");
	}
}
