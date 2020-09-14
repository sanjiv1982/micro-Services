package io.sanjiv.moveicatalogservice.model;

public class Movei {
	
	private String moveiId;
	private String name;
	
	private String overview; 
	
public Movei() {
		
	}
	
	public Movei(String moveiId, String name) {
		
		this.moveiId = moveiId;
		this.name = name;
	}
	public Movei(String moveiId, String name, String overview) {
		// TODO Auto-generated constructor stub
	}

	public String getMoveiId() {
		return moveiId;
	}
	public void setMoveiId(String moveiId) {
		this.moveiId = moveiId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	
	
}
