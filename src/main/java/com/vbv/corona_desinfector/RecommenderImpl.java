package com.vbv.corona_desinfector;


@Deprecated
@Singleton
public class RecommenderImpl implements Recommender {
	
	@InjectProperty()
	private String promotionalProduct;

	@Override
	public void recommend() {
		System.out.println("Advertisment of: " + promotionalProduct);
	}

}
