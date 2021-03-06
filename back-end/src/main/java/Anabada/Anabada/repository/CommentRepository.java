package Anabada.Anabada.repository;

import Anabada.Anabada.domain.Comment;
import Anabada.Anabada.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByPostid(Long postid);
}
