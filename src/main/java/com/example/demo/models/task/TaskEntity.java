package com.example.demo.models.task;

import com.example.demo.models.embeded.Audit;
import com.example.demo.models.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASK")
public class TaskEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "TASK_ID", updatable = false, nullable = false)
    private long id;

    private String title;

    private String content;

    private LocalDateTime endAt;

    private UserEntity author;

    @Embedded
    private Audit audit = new Audit();

    public TaskEntity(){
        super();
    }

    public TaskEntity(String title, String content, LocalDateTime endAt, UserEntity author, Audit audit) {
        this.title = title;
        this.content = content;
        this.endAt = endAt;
        this.author = author;
        this.audit = audit;
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

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", endAt=" + endAt +
                ", author=" + author +
                ", audit=" + audit +
                '}';
    }
}
