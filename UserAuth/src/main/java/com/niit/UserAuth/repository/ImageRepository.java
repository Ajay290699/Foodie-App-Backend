package com.niit.UserAuth.repository;

import com.niit.UserAuth.domain.user.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByName(String imageName);
//
//    public String uploadImageToFile(MultipartFile file) throws IOException;
//
//    public byte[] downloadImageFromFile(String imageName) throws IOException;
}
