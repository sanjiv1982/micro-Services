package io.sanjiv.moveicatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.sanjiv.moveicatalogservice.model.Rating;
import io.sanjiv.moveicatalogservice.model.UserRating;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating",
			
			commandProperties = {
					
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000"),
					 @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					 @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					 @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			
			}
			
			)
	public UserRating getUserRating(@PathVariable("userId") String userId)
	{
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);
	}
	
	public UserRating getFallbackUserRating(@PathVariable("userId") String userId)
	{
		UserRating userRating = new UserRating();
		userRating.setUserid(userId);
		userRating.setUserRating(Arrays.asList(
new Rating("0",0)
				));
		return userRating;
	}

}
