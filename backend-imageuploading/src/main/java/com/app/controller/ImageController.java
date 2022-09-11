package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.IImageService;

@RestController
@CrossOrigin
@RequestMapping("/api/image")
public class ImageController {
	@Autowired
	private IImageService imageService;
	
	@PostMapping("upload_image")
	public ResponseEntity<?> uploadImage(@RequestParam MultipartFile imageFile) throws IOException{
		imageService.storeImage(imageFile);
		return ResponseEntity.status(HttpStatus.OK).body("Image Stored Successfully");
	}
	
	@PutMapping("/upload_multiple_images")
	public ResponseEntity<?> uploadMultipleAppointmentImages(@RequestParam MultipartFile[] imageFile) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(imageService.storeMultipleImages(imageFile));
	}
	@GetMapping("/{imageId}")
	public ResponseEntity<?> getImage(@PathVariable long imageId) throws Exception{
		return ResponseEntity.ok(imageService.restoreImage(imageId));
	}
}
