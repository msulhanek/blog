package sk.posam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.blog.dto.CommentDto;
import sk.posam.blog.exception.PostNotFondException;
import sk.posam.blog.exception.UserNotFoundException;
import sk.posam.blog.model.Comment;
import sk.posam.blog.model.Post;
import sk.posam.blog.model.User;
import sk.posam.blog.repository.CommentRepository;
import sk.posam.blog.repository.PostRepository;
import sk.posam.blog.repository.UserRepository;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CommentService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    public void addComment(Long id, CommentDto commentDto) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFondException("For id: " + id));
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(userRepository.findByUsername(commentDto.getUser()).orElseThrow(
                ()-> new UserNotFoundException("For id: " + commentDto.getUser())));
        comment.setBody(commentDto.getBody());
        comment.setCreatedAt(Instant.now());
        commentRepository.save(comment);
    }


    public Object getAllPostComments(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFondException("For id: " + id));
        List<Comment> comment = commentRepository.findByPost(post);
        return comment.stream().map(this::mapFromCommentToDto).collect(toList());
    }

    private CommentDto mapFromCommentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setUser(comment.getUser().getUsername());
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
    }

    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new PostNotFondException("For id: " + id));
        commentRepository.delete(comment);
    }
}
