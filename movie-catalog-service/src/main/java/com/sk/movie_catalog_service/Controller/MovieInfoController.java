package com.sk.movie_catalog_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.movie_catalog_service.Model.MovieInfo;
import com.sk.movie_catalog_service.Model.MovieRepository;

@RestController
public class MovieInfoController {

	@Autowired
	private MovieRepository movieRepository;
	
	@PostMapping("/movie-info/save")
	public List<MovieInfo> saveAll(@RequestBody List<MovieInfo> movieList) {
		return movieRepository.saveAll(movieList);
	}
	
	@GetMapping("/movie-info/list")
	public List<MovieInfo> getMethodName() {
		return movieRepository.findAll();
	}
	
	@GetMapping("/movie-info/find-path-by-id/{movieInfoId}")
	public String findPathById(@PathVariable Long movieInfoId) {
		var videoInfoOptional = movieRepository.findById(movieInfoId);
		return videoInfoOptional.map(MovieInfo::getPath).orElse(null);
	}
	
	
}
