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

    private String author;

    private Boolean finished;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "TaskBO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", endAt=" + endAt +
                ", author='" + author + '\'' +
                ", finished=" + finished +
                '}';
    }
}
