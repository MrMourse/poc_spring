package com.example.demo.models.task;

import com.example.demo.models.embeded.Audit;
import com.example.demo.models.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASK")
public class TaskEntity extends TaskBO implements Serializable {

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
    @Override
    public long getId() {
        return id;
    }
    @Override
    public void setId(long id) {
        this.id = id;
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String getContent() {
        return content;
    }
    @Override
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public LocalDateTime getEndAt() {
        return endAt;
    }
    @Override
    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }
    @Override
    public UserEntity getAuthor() {
        return author;
    }
    @Override
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
