package Anabada.Anabada.service;

import Anabada.Anabada.domain.AttachmentFile;
import Anabada.Anabada.exception.FileStorageException;
import Anabada.Anabada.exception.MyFileNotFoundException;
import Anabada.Anabada.repository.AttachmentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AttachmentFileService {
    private final AttachmentFileRepository attachmentFileRepository;
    public AttachmentFile storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("실패" + fileName);
            }

            AttachmentFile dbFile = new AttachmentFile(fileName, file.getContentType(), file.getBytes());

            return attachmentFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("저장실패 " + fileName + ". 다시 한번해봐용", ex);
        }
    }

    public AttachmentFile getFile(Long fileId) {
        return attachmentFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("파일을 찾을수없음." + fileId));
    }

}
