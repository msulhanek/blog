package sk.posam.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.posam.blog.dto.CommentDto;
import sk.posam.blog.dto.PostDto;
import sk.posam.blog.service.CommentService;
import sk.posam.blog.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto){
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> showAllPosts(){
        return new ResponseEntity(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<PostDto>> showUserPosts(@PathVariable String username){
        return new ResponseEntity(postService.getUserPosts(username), HttpStatus.OK);
    }


    @PutMapping("get/{id}/edit")
    public ResponseEntity editPost(@PathVariable @RequestBody Long id, @RequestBody PostDto postDto){
        return new ResponseEntity(postService.editPost(postDto, id), HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable @RequestBody Long id){
        return new ResponseEntity(postService.getPost(id), HttpStatus.OK);
    }

    @DeleteMapping("get/{id}")
    public void deletePost(@PathVariable Long id){
        postService.delete(id);
    }

    @PostMapping("get/{id}")
    public ResponseEntity addComment(@PathVariable Long id, @RequestBody CommentDto commentDto){
        commentService.addComment(id, commentDto);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("get/{id}/comments")
    public ResponseEntity<CommentDto> getPostComments(@PathVariable Long id){
        return new ResponseEntity(commentService.getAllPostComments(id), HttpStatus.OK);
    }

    @DeleteMapping("get/{id}/delete/{commentId}")
    public void deleteComment(@PathVariable Long id, @PathVariable Long commentId){
        commentService.delete(commentId);
    }


}
