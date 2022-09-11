package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IVideoService {
	void storeVideo(MultipartFile file)throws IOException;
	
	byte[] restoreVideo(long id)throws Exception;

}
