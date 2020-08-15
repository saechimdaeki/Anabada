package springboot.juseong.anabada.dto;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Post {
    private long id;
    private String title;
    private String content;
    private BigDecimal price;
    private String writer;

    @Nullable
    private List<Comment> comments;

    @Nullable
    private List<file> fiels;

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
