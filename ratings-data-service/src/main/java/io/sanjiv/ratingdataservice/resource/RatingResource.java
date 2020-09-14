package io.sanjiv.ratingdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sanjiv.ratingdataservice.model.Rating;
import io.sanjiv.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
	
	@RequestMapping("/{moveiId}")
	public Rating getRating(@PathVariable("moveiId") String moveiId)
	{
		return new Rating("moveiId",4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId)
	{
		 List<Rating> ratings = Arrays.asList(				  
				  new Rating("1234", 4), 
				  new Rating("5678", 3)
				  );
		 
		 UserRating userRating =  new UserRating();
		 userRating.setUserRating(ratings);
		 return userRating;
	}

}
