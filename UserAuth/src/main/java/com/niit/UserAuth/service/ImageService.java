package com.niit.UserAuth.service;

import com.niit.UserAuth.domain.Image;
import com.niit.UserAuth.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageService {

    private final String filePath = "D:\\Wave 38\\Capstone-Project\\Foodie-App-Backend\\UserAuth\\src\\main\\resources\\Image\\";
    @Autowired
    private ImageRepository imageRepository;

    public String uploadImageToFile(MultipartFile file) throws IOException {
        String imagePath = filePath + file.getOriginalFilename();
        Image image = imageRepository.save(Image.builder().name(file.getOriginalFilename())
                .type(file.getContentType()).filePath(imagePath).build());

        file.transferTo(new File(imagePath));
        if (image != null) {
            return "Image Uploaded successfully : " + imagePath;
        }
        return null;
    }

    public byte[] downloadImageFromFile(String imageName) throws IOException {
        Optional<Image> imageData = imageRepository.findByName(imageName);
        String imagePath = imageData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(imagePath).toPath());
        return images;
    }
}
