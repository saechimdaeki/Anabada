package Anabada.Anabada.controller;


import Anabada.Anabada.domain.AttachmentFile;
import Anabada.Anabada.domain.Comment;
import Anabada.Anabada.domain.FileUrl;
import Anabada.Anabada.domain.Post;
import Anabada.Anabada.repository.AttachmentFileRepository;
import Anabada.Anabada.repository.CommentRepository;
import Anabada.Anabada.repository.FileUriRepository;
import Anabada.Anabada.repository.PostRepository;
import Anabada.Anabada.service.PostService;
import Anabada.Anabada.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;
    private final CommentRepository commentRepository;
    private final FileUriRepository fileUriRepository;


    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post){

        return ResponseEntity.ok().body(this.postService.createPost(post));
    }

    @GetMapping("/post/{id}")
    public Post getPostById(@PathVariable long id){
        List<Comment> comment=commentRepository.findCommentsByPostid(id);
        List<FileUrl> files= fileUriRepository.findFileUrlByPostid(id);
        Post post=postService.getPostById(id);
        post.setComments(comment);
        post.setFiles(files);
        return post;
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id,@RequestBody Post post){
        post.setId(id);
        return ResponseEntity.ok().body(this.postService.updatePost(post));
    }

    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable long id){
        this.postService.deletePost(id);
        return "OK";
    }

    @GetMapping("/post/")
    public List<Post> gettype(@RequestParam("type") String type){
        return postService.getPostByType(type);
    }

    @GetMapping("/post/title")
    public Post getpost(@RequestParam("title") String title){
        return postService.getPostbyTitle(title);
    }

}
