package com.vbv.corona_desinfector;


//@Singletone
public class RecommenderImpl implements Recommender {
	
	@InjectProperty()
	private String promotionalProduct;

	@Override
	public void recommend() {
		System.out.println("Advertisment of: " + promotionalProduct);
	}

}
