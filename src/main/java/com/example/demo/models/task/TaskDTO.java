package com.example.demo.models.task;

import com.example.demo.models.user.UserEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Data Transfert Objet task, il fait le lien avec le service extérieur.
 */
@XmlRootElement(name = "task")
public class TaskDTO implements Serializable {

    public static final long serialVersionUID = 2010783855604743831L;

    private long id;

    private String title;

    private String content;

    private LocalDateTime endAt;

    private String author;

    private Boolean finished;

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

    public String getAuthor() {
        return author;
    }
    @XmlElement
    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getFinished() {
        return finished;
    }

    @XmlElement
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", endAt=" + endAt +
                ", author='" + author + '\'' +
                ", finished=" + finished +
                '}';
    }

    /**
     * Permet de corriger une erreur dans l'objet à modifier.
     * Il prend en priorité l'id du paramètre de la requete et s'il est différent de l'objet.
     * On le modifie avec l'id du paramètre de la la requete.
     *
     * @param id, l'id de l'objet à modifier.
     */
    public void setIdUpdate(Long id){
        if (this.getId() != id){
            this.setId(id);
        }
    }
}
