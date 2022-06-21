package com.vbv.corona_desinfector;

@Singleton
public class AngryPolicemen implements Policemen {

	@Override
	public void policeWork() {
		System.out.println("Angry policemen says: GTFO");

	}
	
//	@PostConstruct
//	public void init() {
//		System.out.println(this.getClass() + " POST CONSTRUCT");
//	}

}
