package Anabada.Anabada.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Getter @Setter
public class AttachmentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    public AttachmentFile() {

    }

    public AttachmentFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}
