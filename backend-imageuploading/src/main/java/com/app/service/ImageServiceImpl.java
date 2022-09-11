package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Clock;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.entities.Image;
import com.app.repository.ImageRepository;

@Service
@Transactional
public class ImageServiceImpl implements IImageService {
	@Value("${file.upload.location}")
	private String baseFolder;
	@Autowired
	private ImageRepository imageRepository;

	@Override
	public void storeImage(MultipartFile file) throws IOException {
		Image image = new Image();

		String completePath = baseFolder + File.separator + Clock.systemDefaultZone().millis()
				+ file.getOriginalFilename();
		System.out.println("complete path " + completePath);
		long bytes = Files.copy(file.getInputStream(), Paths.get(completePath), StandardCopyOption.REPLACE_EXISTING);

		// save complete path to the image in db
		// --call setter on emp !
		image.setImagePath(completePath);// save complete path to the file in db

		// to save to db following line only
		// not recommended to save big file in DB
		image.setImage(file.getBytes());

		imageRepository.save(image);
	}

	@Override
	public List<String> storeMultipleImages(MultipartFile[] imageFiles) throws IOException {
		List<String> list = new ArrayList<>();
		List<String> fileNames = new ArrayList<>();
		try {

			Arrays.asList(imageFiles).stream().forEach(file -> {

				Clock clock = Clock.systemDefaultZone();
				long milliSeconds = clock.millis();
				String completePath = baseFolder + File.separator + milliSeconds + file.getOriginalFilename();
				try {
					Files.copy(file.getInputStream(), Paths.get(completePath), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
				list.add(completePath);

				fileNames.add(file.getOriginalFilename());

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		list.forEach(file ->{
			Image image = new Image();
			image.setImagePath(file);
			imageRepository.save(image);
		});
		
		return fileNames;
	}
	
	@Override
	public byte[] restoreImage(long id) throws Exception {
		Image image = imageRepository.findById(id).orElseThrow(()-> new Exception("Image Id not found"));
		return Files.readAllBytes(Paths.get(image.getImagePath()));
	}

}
