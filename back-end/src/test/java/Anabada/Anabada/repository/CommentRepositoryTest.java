package Anabada.Anabada.repository;

import Anabada.Anabada.domain.Comment;
import Anabada.Anabada.domain.Post;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;
    @After
    public void cleanup(){
        commentRepository.deleteAll();
        postRepository.deleteAll();
    }


    @Test
    public void 불러온것_확인(){
        long id=1;
        //given
        String title="댓글 제목";
        String content="댓글 내용";
        String writer="댓글 쓴사람";
        Optional<Post> post=postRepository.findById(id);
        Comment comment=new Comment();
        comment.setPostid(id);
        comment.setWriter(writer);
        comment.setContent(content);
        comment.setTitle(title);
        commentRepository.save(comment);

        //when
        List<Comment> comments=commentRepository.findCommentsByPostid(id);
        //then
        Comment comment1=comments.get(0);
        assertThat(comment1.getTitle()).isEqualTo(title);

    }
}