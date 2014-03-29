package fr.eneid.android.eneidandroid.app;

public class Message {

    private long authorId;
    private String picUrl;
    private String content;
    private String name;

    public Message(long authorId, String name, String picUrl, String content) {
        this.authorId = authorId;
        this.picUrl = picUrl;
        this.content = content;
        this.name = name;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
