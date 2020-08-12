package Anabada.Anabada.repository;

import Anabada.Anabada.domain.AttachmentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentFileRepository extends JpaRepository<AttachmentFile,Long> {
}
