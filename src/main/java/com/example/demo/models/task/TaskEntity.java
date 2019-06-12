package com.example.demo.models.task;

import com.example.demo.models.embeded.Audit;
import com.example.demo.models.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Objet Entity task, il fait le lien avec la base de données.
 */
@Entity
@Table(name = "TASK")
public class TaskEntity implements Serializable {

    public static final long serialVersionUID = 7616013285801038751L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "TASK_ID", updatable = false, nullable = false)
    private long id;

    private String title;

    private String content;

    private LocalDateTime endAt;

    private String author;

    private Boolean finished;

    @Embedded
    private Audit audit = new Audit();

    public TaskEntity(){
        super();
    }

    public TaskEntity(String title, String content, LocalDateTime endAt, String author, Audit audit, Boolean finished) {
        this.title = title;
        this.content = content;
        this.endAt = endAt;
        this.author = author;
        this.audit = audit;
        this.finished = finished;
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

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", endAt=" + endAt +
                ", author='" + author + '\'' +
                ", finished=" + finished +
                ", audit=" + audit +
                '}';
    }
}
