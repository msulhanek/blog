package sk.posam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.posam.blog.model.Comment;
import sk.posam.blog.model.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
