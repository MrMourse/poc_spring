package com.example.demo.models.user;

import com.example.demo.models.embeded.Audit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;


@Entity
@Table(name = "USERS")
public class UserEntity extends UserBO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "USER_ID", updatable = false, nullable = false)
    private long id;

    @NotNull(message = "Please, inform a name for your user.")
    private String name = "";

    @Embedded
    private Audit audit = new Audit();

    public UserEntity() {
        super();
    }
    public UserEntity(Long id) {
        this.id = id;
    }

    public UserEntity(String name) {
        this.name = name;
    }

    public UserEntity(@NotNull(message = "Please, inform a name for your user.") String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public UserEntity(Long id, String name) {
        this.id = id;
        this.name = name;
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
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String getMail() {
        return mail;
    }
    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }
    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}