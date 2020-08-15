package Anabada.Anabada.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter @Setter
public class FileUrl {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileurl_id")
    private Long id;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "fileDownloadUri")
    private String downloaduri;


    @Column(name = "size")
    private long size;

    @Column(name = "postid")
    private Long postid;

    @Lob
    private byte[] data;


}
