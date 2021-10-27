package sk.posam.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sk.posam.blog.service.FileUploadService;

import java.io.IOException;


@RestController
@RequestMapping("/api/upload")
public class ImageController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        fileUploadService.uploadFile(file);
        return new ResponseEntity(HttpStatus.OK);
    }

}
