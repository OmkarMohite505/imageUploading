package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.IVideoService;

@RestController
@CrossOrigin
@RequestMapping("/api/video")
public class VideoController {
	@Autowired
	private IVideoService videoService;
	
	
	@PostMapping("/upload_video")
	public ResponseEntity<?> uploadVideo(@RequestParam MultipartFile videoFile) throws IOException{
		videoService.storeVideo(videoFile);
		return ResponseEntity.status(HttpStatus.OK).body("Video Uploaded");
	}
	@GetMapping("/{videoId}")
	public ResponseEntity<?> getVideo(@PathVariable long videoId) throws Exception{
		return ResponseEntity.ok(videoService.restoreVideo(videoId));
	}


}
