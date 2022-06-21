package com.vbv.corona_desinfector;

//1 responsibility
@Singleton
@Deprecated
public class CoronaDesinfector {

	// @Autowired analog
	@InjectByType
	private Announcer announcer ;
	
	// @Autowired analog
	@InjectByType
	private Policemen policemen ;

	public void start(Room room) {
		announcer.firstAnnounce();
		policemen.policeWork();
		desinfectRoom(room);
		announcer.secondAnnounce();
	}

	// 1(main) responsibility
	public void desinfectRoom(Room room) {
		System.out.println(room.roomPurpose + " desinfected");
	}
}
