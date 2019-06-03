package com.example.demo.models.task;

import com.example.demo.models.user.UserEntity;

import java.time.LocalDateTime;

/**
 * Business Object task, il permet d'effectuer les divers traitements liés aux règles métiers.
 */
public class TaskBO {

    private long id;

    private String title;

    private String content;

    private LocalDateTime endAt;

    private UserEntity author;

    public TaskBO(){
        super();
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

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "TaskBO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", endAt=" + endAt +
                ", author=" + author +
                '}';
    }
}
