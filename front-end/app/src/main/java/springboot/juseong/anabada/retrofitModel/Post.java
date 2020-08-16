package springboot.juseong.anabada.retrofitModel;

import androidx.annotation.Nullable;

import androidx.annotation.Nullable;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public class Post {
    private long id;
    private String title;
    private String content;
    private BigDecimal price;
    private String writer;

    @Nullable
    private List<Comment> comments;

    @Nullable
    private List<FileUrl> files;


    @Nullable
    public List<FileUrl> getFiles() {
        return files;
    }

    public void setFiles(@Nullable List<FileUrl> files) {
        this.files = files;
    }

    @Nullable
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(@Nullable List<Comment> comments) {
        this.comments = comments;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Post(String title, String content, BigDecimal price, String writer) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.writer = writer;
    }
}
