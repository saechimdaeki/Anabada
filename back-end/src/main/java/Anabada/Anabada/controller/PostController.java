package Anabada.Anabada.controller;


import Anabada.Anabada.domain.Post;
import Anabada.Anabada.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        return ResponseEntity.ok().body(this.postService.createPost(post));
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id){
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id,@RequestBody Post post){
        post.setId(id);
        return ResponseEntity.ok().body(this.postService.updatePost(post));
    }

    @DeleteMapping("/post/{id}")
    public HttpStatus deletePost(@PathVariable long id){
        this.postService.deletePost(id);
        return HttpStatus.OK;
    }
}
