package Anabada.Anabada.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter @Setter
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "comment_id")
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "writer")
    private String writer;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
