package Anabada.Anabada.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "member")
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column (name = "content")
    private String content;
}
