package Anabada.Anabada.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter @Setter
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name ="email")
    private String email;

    @Column(name = "password")
    private String password;
}
