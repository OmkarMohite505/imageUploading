package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Image;
import com.app.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
