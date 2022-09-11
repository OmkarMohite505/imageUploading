package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Clock;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.entities.Image;
import com.app.entities.Video;
import com.app.repository.VideoRepository;

@Service
@Transactional
public class VideoServiceImpl implements IVideoService {
	@Value("${file.video.location}")
	private String baseFolder;
	@Autowired
	private VideoRepository videoRepository;
	
	@Override
	public void storeVideo(MultipartFile file) throws IOException {
		Video video = new Video();

		String completePath = baseFolder + File.separator + Clock.systemDefaultZone().millis()
				+ file.getOriginalFilename();
		System.out.println("complete path " + completePath);
		long bytes = Files.copy(file.getInputStream(), Paths.get(completePath), StandardCopyOption.REPLACE_EXISTING);

		// save complete path to the image in db
		// --call setter on emp !
		video.setVideoPath(completePath);// save complete path to the file in db

		// to save to db following line only
		// not recommended to save big file in DB
//		video.setVideo(file.getBytes());

		videoRepository.save(video);
	}
	@Override
	public byte[] restoreVideo(long id) throws Exception {
		Video video = videoRepository.findById(id).orElseThrow(()-> new Exception("Image Id not found"));
		return Files.readAllBytes(Paths.get(video.getVideoPath()));
	}
}