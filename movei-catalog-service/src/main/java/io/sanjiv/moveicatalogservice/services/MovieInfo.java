package io.sanjiv.moveicatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.sanjiv.moveicatalogservice.model.CatalogItem;
import io.sanjiv.moveicatalogservice.model.Movei;
import io.sanjiv.moveicatalogservice.model.Rating;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating)
	{
		Movei movei = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMoveiId(), Movei.class);
		return  new CatalogItem(movei.getName(), "Test", rating.getRatings());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating)
	{
		return  new CatalogItem("Movie name not found", "", rating.getRatings());
	}

}
