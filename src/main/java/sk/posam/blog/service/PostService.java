package sk.posam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import sk.posam.blog.dto.PostDto;
import sk.posam.blog.exception.PostNotFondException;
import sk.posam.blog.exception.UserNotFoundException;
import sk.posam.blog.model.Post;
import sk.posam.blog.repository.PostRepository;
import sk.posam.blog.repository.UserRepository;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public void createPost(PostDto postDto){
        sk.posam.blog.model.User user = userRepository.findByUsername(postDto.getUsername()).orElseThrow(
                ()-> new UserNotFoundException("For username: " + postDto.getUsername()));
        user.setNumberOfPosts(user.getNumberOfPosts()+1);
        Post post = mapFromDtoToPost(postDto);
        postRepository.save(post);
    }

    public Object showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getAuthor());
        postDto.setCreatedAt(post.getCreated());
        postDto.setTitleImg(post.getTitleImg());
        return postDto;
    }

    private Post mapFromDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User username = authService.getCurrentUser().orElseThrow(
                () -> new IllegalArgumentException("No user logged in"));
        post.setAuthor(username.getUsername());
        post.setCreated(Instant.now());
        post.setTitleImg(postDto.getTitleImg());
        return post;
    }

    public Object getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFondException("For id: " + id));
        return mapFromPostToDto(post);
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFondException("For id: " + id));
        postRepository.delete(post);
    }

    public Object editPost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new PostNotFondException("For id: " + postDto.getId()));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setTitleImg(postDto.getTitleImg());
        postRepository.save(post);
        return post;
    }

    public Object getUserPosts(String username) {
        List<Post> posts = postRepository.findByAuthor(username);
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }
}
