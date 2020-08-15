package Anabada.Anabada.repository;

import Anabada.Anabada.domain.Post;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Test
    public void 게시글불러오기(){
        //given
        Post post=new Post();
        String title="테스트 게시글";
        String content="테스트 본문";
        String writer="테스트 작성자";
        BigDecimal big=new BigDecimal(12345);
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        post.setPrice(big);
        postRepository.save(post);
        //when
        List<Post> posts= postRepository.findAll();
        //then
        Post post1=posts.get(0);
        assertThat(post1.getTitle()).isEqualTo(title);
        assertThat(post1.getContent()).isEqualTo(content);
    }
}