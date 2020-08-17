package Anabada.Anabada.service;

import Anabada.Anabada.domain.Comment;
import Anabada.Anabada.domain.FileUrl;
import Anabada.Anabada.exception.PostNotFoundException;
import Anabada.Anabada.domain.Post;
import Anabada.Anabada.repository.CommentRepository;
import Anabada.Anabada.repository.FileUriRepository;
import Anabada.Anabada.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final FileUriRepository fileUriRepository;
    @Override
    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Post updatePost(Post post) {
        Optional<Post> postdb=this.postRepository.findById(post.getId());
        if(postdb.isPresent()){
            Post postUpdate=postdb.get();
            postUpdate.setId(post.getId());
            postUpdate.setTitle(post.getTitle());
            postUpdate.setType(post.getType());
            postUpdate.setContent(post.getContent());
            postRepository.save(postUpdate);
            return postUpdate;
        }else{
            throw new PostNotFoundException("찾지못하였습니다.. "+post.getId());
        }

    }

    @Override
    @Transactional
    public List<Post> getAllPosts() {
        List<Post> postall= new ArrayList<>();

        for(Post post:postRepository.findAll())
        {
            List<Comment> comments= commentRepository.findCommentsByPostid(post.getId());
            List<FileUrl> files=fileUriRepository.findFileUrlByPostid(post.getId());
            post.setComments(comments);
            post.setFiles(files);
            postall.add(post);
        }
        return postall;
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
    @Transactional
    public void deletePost(long id) {
        Optional<Post> postDb=this.postRepository.findById(id);
        if(postDb.isPresent()){
            this.postRepository.delete(postDb.get());
        }else{
            throw new PostNotFoundException("찾지못하였습니다.."+id);
        }
    }

    public List<Post> getPostByType(String type){
        List<Post> posts= postRepository.findAllByType(type);
        for(Post post:posts){
            List<Comment> comments=commentRepository.findCommentsByPostid(post.getId());
            List<FileUrl> files=fileUriRepository.findFileUrlByPostid(post.getId());
            post.setComments(comments);
            post.setFiles(files);
        }
        return posts;
    }

    public Post getPostbyTitle(String title){
        Post post= postRepository.findByTitle(title);
        List<Comment> comments= commentRepository.findCommentsByPostid(post.getId());
        List<FileUrl> fileUrls= fileUriRepository.findFileUrlByPostid(post.getId());
        post.setComments(comments);
        post.setFiles(fileUrls);
        return post;
    }
}
