package com.example.demo.models.task;

import com.example.demo.models.user.UserEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;


@XmlRootElement(name = "task")
public class TaskDTO extends TaskBO implements Serializable {


    public TaskDTO(){
        super();
    }
    @Override
    public long getId() {
        return id;
    }

    @Override
    @XmlElement
    public void setId(long id) {
        super.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    @XmlElement
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public LocalDateTime getEndAt() {
        return endAt;
    }
    @Override
    @XmlElement
    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }
    @Override
    public UserEntity getAuthor() {
        return author;
    }
    @Override
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
