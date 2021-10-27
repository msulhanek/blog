package sk.posam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.posam.blog.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(String username);
}
