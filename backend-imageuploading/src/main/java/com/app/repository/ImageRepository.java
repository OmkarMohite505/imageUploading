package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
