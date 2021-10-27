package sk.posam.blog.model;

import org.springframework.beans.factory.annotation.Autowired;
import sk.posam.blog.service.UserService;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;
    @ManyToOne
    private Post post;
    private String body;
    private Instant createdAt;

    public Comment() {
    }

    public Comment(Long id, User user, Post post, String body, Instant createdAt) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.body = body;
        this.createdAt = createdAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
