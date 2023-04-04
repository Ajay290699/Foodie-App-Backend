package com.niit.UserAuth.repository;

import com.niit.UserAuth.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByName(String imageName);
}
