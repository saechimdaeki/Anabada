package Anabada.Anabada.repository;

import Anabada.Anabada.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
   // List<Post> findAllByI

}
