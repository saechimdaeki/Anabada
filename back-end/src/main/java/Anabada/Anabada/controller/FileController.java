package Anabada.Anabada.controller;

import Anabada.Anabada.domain.AttachmentFile;
import Anabada.Anabada.domain.Post;
import Anabada.Anabada.dto.UploadFileResponse;
import Anabada.Anabada.repository.AttachmentFileRepository;
import Anabada.Anabada.service.AttachmentFileService;
import Anabada.Anabada.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final AttachmentFileService attachmentFileService;
    private final PostService postService;
    private final AttachmentFileRepository attachmentFileRepository;

    /**
     * post id값에 singleFile 업로드
     * @param id
     * @param file
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/post/{id}/upload")
    public UploadFileResponse uploadFile(@PathVariable Long id,@RequestParam("file") MultipartFile file) {
        Post post=postService.getPostById(id);
        AttachmentFile dbFile = attachmentFileService.storeFile(file);
        dbFile.setPost(post);
        attachmentFileRepository.save(dbFile);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId().toString())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    /**
     * postid에 파일들이 매칭됨
     * @param id
     * @param files
     * @return
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/post/{id}/uploadfiles")
    public List<UploadFileResponse> uploadMultipleFiles(@PathVariable Long id,@RequestParam("file") MultipartFile[] files) {
       Post post=postService.getPostById(id);

       return Arrays.stream(files)
                .map((MultipartFile id1) -> uploadFile(id,id1))
                .collect(Collectors.toList());
    }

    @GetMapping("/post/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long postId,@PathVariable Long fileId) {
        AttachmentFile dbFile = attachmentFileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
