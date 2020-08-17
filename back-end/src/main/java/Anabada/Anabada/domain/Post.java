package Anabada.Anabada.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "writer")
    private String writer;

    @Column(name = "type")
    private String type;

    @Transient
    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "comments")
    private List<Comment> comments=new ArrayList<>();

    @Transient
    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "files")
    private List<FileUrl> files=new ArrayList<>();


   // @CreationTimestamp
   //private Date createdDate;

   // @CreationTimestamp
   // private Date updateDate;







}
