package Anabada.Anabada.service;


import Anabada.Anabada.domain.Comment;
import Anabada.Anabada.domain.Post;
import Anabada.Anabada.repository.CommentRepository;
import Anabada.Anabada.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public List<Comment> getAllComments(Long id){
        return commentRepository.findCommentsByPostid(id);
    }

    @Transactional
    public Comment createComment(Long id,Comment comment){
        comment.setPostid(id);
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Comment comment)
    {
        commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public String deleteComment(Long commentId){
        Comment comment=commentRepository.findById(commentId).get();
        commentRepository.delete(comment);
        return "삭제 성공이용!";
    }
}
