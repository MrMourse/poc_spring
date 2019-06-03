package com.example.demo.models.task;

import com.example.demo.models.user.UserEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;


@XmlRootElement(name = "task")
public class TaskDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "TASK_ID", updatable = false, nullable = false)
    private long id;

    private String title;

    private String content;

    private LocalDateTime endAt;

    private UserEntity author;

    public TaskDTO(){
        super();
    }

    public long getId() {
        return id;
    }
    @XmlElement
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    @XmlElement
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }
    @XmlElement
    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public UserEntity getAuthor() {
        return author;
    }
    @XmlElement
    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", endAt=" + endAt +
                ", author=" + author +
                '}';
    }
}
