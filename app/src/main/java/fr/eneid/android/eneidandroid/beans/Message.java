package fr.eneid.android.eneidandroid.beans;

import java.util.Date;

public class Message {

    private long id;
    private Author author;
    private long date;
    private String contents;
    private String action;

    public Message() {
    }

    public Message(long id, Author author, long date, String contents, String action) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.contents = contents;
        this.action = action;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
