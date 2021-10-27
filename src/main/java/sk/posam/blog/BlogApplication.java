package sk.posam.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sk.posam.blog.dto.RegisterRequest;

@SpringBootApplication
public class BlogApplication extends RegisterRequest {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
