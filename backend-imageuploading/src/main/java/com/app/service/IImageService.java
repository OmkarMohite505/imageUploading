package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
	void storeImage(MultipartFile file)throws IOException;
	
	List<String> storeMultipleImages(MultipartFile[] files) throws IOException;
	
	byte[] restoreImage(long id)throws Exception;
	

}
