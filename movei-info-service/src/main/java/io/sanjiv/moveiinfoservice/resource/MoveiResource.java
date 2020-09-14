package io.sanjiv.moveiinfoservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.sanjiv.moveiinfoservice.model.Movei;
import io.sanjiv.moveiinfoservice.model.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MoveiResource {
	
	@Value("${api.key}")
	private String apiKey;
	
	/*
	 * @Autowired private RestTemplate restTemplate;
	 */
	
	@RequestMapping("/{moveiId}")
	public Movei getMoveiInfo(@PathVariable("moveiId") String moveiId)
	{
		//open this to fetch dynamic name of movei
		/*
		 * RestTemplate restTemplate = new RestTemplate(); MovieSummary movieSummary =
		 * restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+moveiId+
		 * "?api_key="+apiKey, MovieSummary.class);
		 * 
		 * return new Movei(moveiId,movieSummary.getTitle(),
		 * movieSummary.getOverview());
		 */
		return new Movei("MoveiId", "Test name");
	}
	

}
