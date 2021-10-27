package sk.posam.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {

    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File("C:/Users/Marek/Desktop/blog-ui/blog-ui/src/assets/images/" + file.getOriginalFilename()));
    }
}
