package com.vbv.corona_desinfector;



public class CoronaDesinfector {


	
	
	public void start(Room room) {
		System.out.println("Annoncment: We urge everyone to leave the room");
		System.out.println("Policemen:  We urge the remaining people to leave the room");
		desinfectRoom(room);
		System.out.println("Annoncment: People can go back to the room");
	}
	
	public void desinfectRoom(Room room) {
		System.out.println(room.roomPurpose + " desinfected");
	}
}
