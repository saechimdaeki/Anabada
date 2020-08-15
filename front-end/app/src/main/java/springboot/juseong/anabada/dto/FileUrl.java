package springboot.juseong.anabada.dto;



public class FileUrl {
    private Long id;
    private String fileName;
    private String downloaduri;
    private long size;
    private Long postid;
    private byte[] data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloaduri() {
        return downloaduri;
    }

    public void setDownloaduri(String downloaduri) {
        this.downloaduri = downloaduri;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
