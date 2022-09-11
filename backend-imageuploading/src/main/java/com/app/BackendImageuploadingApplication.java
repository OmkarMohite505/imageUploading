package com.app;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendImageuploadingApplication implements CommandLineRunner {
	@Value("${file.upload.location}")  // we are getting folder name for storing images in that folder
	// which is declared in application.properties file
	private String folderName;
	@Value("${file.video.location}") 
	private String videoFolderName;

	public static void main(String[] args) {
		SpringApplication.run(BackendImageuploadingApplication.class, args);
	}
	//We need to check folder exists or not at the time of starting app
	@Override
	public void run(String... args) throws Exception {
		File dir=new File(folderName);
		if(!dir.exists()) {
			dir.mkdirs();
			System.out.println("Folder not exists, Created folder for storing image");
		}
		else 
			System.out.println("folder already exists");
		File dirForVideo = new File(videoFolderName);
		if(!dirForVideo.exists())
			dirForVideo.mkdirs();
	}
	

}
