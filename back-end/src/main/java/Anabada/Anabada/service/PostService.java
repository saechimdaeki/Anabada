package Anabada.Anabada.service;

import Anabada.Anabada.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post);
    Post updatePost(Post post);
    List<Post> getAllPosts();
    Post getPostById(long id);
    void deletePost(long id);

}
