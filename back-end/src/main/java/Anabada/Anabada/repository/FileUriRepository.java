package Anabada.Anabada.repository;

import Anabada.Anabada.domain.FileUrl;
import Anabada.Anabada.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileUriRepository extends JpaRepository<FileUrl,Long> {
    List<FileUrl> findFileUrlByPost(Post post);
}
