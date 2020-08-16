package Anabada.Anabada.service;

import Anabada.Anabada.domain.FileUrl;
import Anabada.Anabada.exception.PostNotFoundException;
import Anabada.Anabada.repository.FileUriRepository;
import Anabada.Anabada.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileUriService {

    private final FileUriRepository fileUriRepository;
    private final PostRepository postRepository;

    @Transactional
    public void uploadFile(FileUrl fileUrl){
        fileUriRepository.save(fileUrl);
    }

    public Optional<FileUrl> findbyfileid(Long fileid){
        return fileUriRepository.findById(fileid);
    }


    public List<FileUrl> findAllFileUrl(Long postid){
        return fileUriRepository.findFileUrlByPostid(postid);
    }

    @Transactional
    public FileUrl updateFile(FileUrl fileUrl)
    {
        Optional<FileUrl> fileUrl1=this.fileUriRepository.findById(fileUrl.getId());
        if(fileUrl1.isPresent()){
            FileUrl fileUpdate=fileUrl1.get();
            fileUpdate.setId(fileUrl.getId());
            fileUpdate.setData(fileUrl.getData());
            fileUpdate.setSize(fileUrl.getSize());
            fileUpdate.setPostid(fileUrl.getPostid());
            fileUpdate.setFileName(fileUrl.getFileName());

            fileUriRepository.save(fileUpdate);
            return fileUpdate;
        }else{
            throw new PostNotFoundException("찾지못하였습니다.. "+fileUrl.getId());
        }
    }


}
