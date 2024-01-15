package be.ehb.mesdoigtsdefeesapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public
class Post {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Title;
    private String Content;
    private String Size;


    public Post() {
    }

    public Post(String title, String content, String size) {
        Title = title;
        Content = content;
        Size = size;

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
