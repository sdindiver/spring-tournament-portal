package com.bo.tournament.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/")
public class WeatherController {

	
	
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public String getWeather() throws URISyntaxException {
		ResponseEntity<String> response=null;
		RestTemplate template = new RestTemplate();
		String weatherAPIUrl = System.getenv("WEATHER_API");
		 response = new ResponseEntity<>("BLANK",HttpStatus.OK);
		if(weatherAPIUrl !=null && !weatherAPIUrl.isEmpty()){
			URI weatherURI = new URI(weatherAPIUrl);
			response= template.getForEntity(weatherURI, String.class);
		}
		
		return response.getBody();
		
	}

}
