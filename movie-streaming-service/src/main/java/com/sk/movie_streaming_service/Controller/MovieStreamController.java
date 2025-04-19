package com.sk.movie_streaming_service.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MovieStreamController {

	public static final Logger log = Logger.getLogger(MovieStreamController.class.getName());
	
	
	@Autowired
	public MovieCatalogService movieCatalogService;
	
	public static final String DIRECTORY = "D:\\videos\\";
	
	@GetMapping("/stream/{videoPath}")
	public ResponseEntity<InputStreamResource> streamingVideo(@PathVariable String videoPath) throws FileNotFoundException{
		File file = new File(DIRECTORY + videoPath);
		if(file.exists()) {
			InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("video/mp4"))
			.body(inputStreamResource);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping("/stream/with-id/{videoInfoId}")
	public ResponseEntity<InputStreamResource> streamingVideoById(@PathVariable Long videoInfoId) throws FileNotFoundException{
		String moviePath = movieCatalogService.getMoviePath(videoInfoId);
		log.log(Level.INFO,"Resolved movie Path ={0} "+moviePath);
		return streamingVideo(moviePath);
	}
	
	
	
}
