package io.sanjiv.moveicatalogservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.sanjiv.moveicatalogservice.model.CatalogItem;
import io.sanjiv.moveicatalogservice.model.Movei;
import io.sanjiv.moveicatalogservice.model.Rating;
import io.sanjiv.moveicatalogservice.model.UserRating;
import io.sanjiv.moveicatalogservice.services.MovieInfo;
import io.sanjiv.moveicatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MoveiCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	/*
	 * @Autowired private WebClient.Builder webClientBuilder;
	 */
	
	@RequestMapping("/{userId}")
	// @HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
	{
	
		//get all rated movei Ids
		
	//	UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
		UserRating ratings = userRatingInfo.getUserRating(userId);
		//return ratings.getUserRating().stream().map(rating -> {
		return ratings.getUserRating().stream().map(rating -> 
		movieInfo.getCatalogItem(rating)).collect(Collectors.toList());
			// for each movie Ids call movie info service and get detials 
			//Movei movei = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMoveiId(), Movei.class);
			//return  new CatalogItem(movei.getName(), "Test", rating.getRatings());
			//put them all together
			//}).collect(Collectors.toList());
		
	}
	
	//put this code in other service class MovieInfo
	
	/*
	 * @HystrixCommand(fallbackMethod = "getFallbackCatalogItem") private
	 * CatalogItem getCatalogItem(Rating rating) { Movei movei =
	 * restTemplate.getForObject("http://movie-info-service/movies/"+rating.
	 * getMoveiId(), Movei.class); return new CatalogItem(movei.getName(), "Test",
	 * rating.getRatings()); }
	 * 
	 * private CatalogItem getFallbackCatalogItem(Rating rating) { return new
	 * CatalogItem("Movie name not found", "", rating.getRatings()); }
	 */
	
	//put this code in other service class UserRatingInfo
	/*
	 * @HystrixCommand(fallbackMethod = "getFallbackUserRating") private UserRating
	 * getUserRating(@PathVariable("userId") String userId) { return
	 * restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+
	 * userId, UserRating.class); }
	 * 
	 * private UserRating getFallbackUserRating(@PathVariable("userId") String
	 * userId) { UserRating userRating = new UserRating();
	 * userRating.setUserid(userId); userRating.setUserRating(Arrays.asList( new
	 * Rating("0",0) )); return userRating; }
	 */
	
	//public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId)
	//{
	//	return Arrays.asList(new CatalogItem("No Movie","", 0));
	//}

}
