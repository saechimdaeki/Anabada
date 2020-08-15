package Anabada.Anabada.repository;

import Anabada.Anabada.domain.AttachmentFile;

import Anabada.Anabada.domain.Comment;
import Anabada.Anabada.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentFileRepository extends JpaRepository<AttachmentFile,Long> {
    List<AttachmentFile> findFilesByPostid(Long id);


}
