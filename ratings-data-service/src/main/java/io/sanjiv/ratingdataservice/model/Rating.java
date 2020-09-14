package io.sanjiv.ratingdataservice.model;

public class Rating {

	private String moveiId;
	private int ratings;
	
	
	
	
	public Rating(String moveiId, int ratings) {
		
		this.moveiId = moveiId;
		this.ratings = ratings;
	}
	public String getMoveiId() {
		return moveiId;
	}
	public void setMoveiId(String moveiId) {
		this.moveiId = moveiId;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	
	
	
	
	
	
}
