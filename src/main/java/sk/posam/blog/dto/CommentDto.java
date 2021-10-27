package sk.posam.blog.dto;

import org.springframework.beans.factory.annotation.Autowired;
import sk.posam.blog.service.PostService;
import sk.posam.blog.service.UserService;

import java.time.Instant;

public class CommentDto {
    private Long id;
    private String user;
    private String body;
    private String post;
    private Instant createdAt;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
