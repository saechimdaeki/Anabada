package Anabada.Anabada.controller;


import Anabada.Anabada.domain.Comment;
import Anabada.Anabada.domain.Post;
import Anabada.Anabada.repository.CommentRepository;
import Anabada.Anabada.repository.PostRepository;
import Anabada.Anabada.service.CommentService;
import Anabada.Anabada.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final PostService postService;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/post/{id}/comment")
    public List<Comment> getComments(@PathVariable Long id){
        return commentService.getAllComments(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/post/{id}/comment/{commentId}")
    public Comment updateComment(@PathVariable Long id, @PathVariable Long commentId,
                                 @RequestBody Comment comment){
        comment.setPostid(id);
        Comment newCom=commentRepository.findById(commentId).get();
        newCom.setTitle(comment.getTitle());
        newCom.setContent(comment.getContent());
        newCom.setWriter(comment.getWriter());
        commentService.updateComment(newCom);
        return newCom;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/post/{id}/comment")
    public Comment createComment(@PathVariable Long id,@RequestBody Comment comment){
        return commentService.createComment(id,comment);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/post/{id}/comment/{commentId}")
    public String deleteComment(@PathVariable Long id,@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }
}
