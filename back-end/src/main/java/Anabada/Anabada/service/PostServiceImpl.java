package Anabada.Anabada.service;

import Anabada.Anabada.exception.PostNotFoundException;
import Anabada.Anabada.model.Post;
import Anabada.Anabada.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        Optional<Post> postdb=this.postRepository.findById(post.getId());
        if(postdb.isPresent()){
            Post postUpdate=postdb.get();
            postUpdate.setId(post.getId());
            postUpdate.setName(post.getName());
            postUpdate.setDescription(post.getDescription());
            postRepository.save(postUpdate);
            return postUpdate;
        }else{
            throw new PostNotFoundException("찾지못하였습니다.. "+post.getId());
        }

    }

    @Override
    public List<Post> getAllPosts() {
        return this.postRepository.findAll();
    }

    @Override
    public Post getPostById(long id) {
        Optional<Post> postDb=this.postRepository.findById(id);
        if(postDb.isPresent()){
            return postDb.get();
        }else{
            throw new PostNotFoundException("찾지못하였습니다.."+id);
        }
    }

    @Override
    public void deletePost(long id) {
        Optional<Post> postDb=this.postRepository.findById(id);
        if(postDb.isPresent()){
            this.postRepository.delete(postDb.get());
        }else{
            throw new PostNotFoundException("찾지못하였습니다.."+id);
        }
    }
}
