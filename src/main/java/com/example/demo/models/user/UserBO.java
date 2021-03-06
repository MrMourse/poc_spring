package com.example.demo.models.user;

import java.util.Objects;
/**
 * Business Object user, il permet d'effectuer les divers traitements liés aux règles métiers.
 */
public class UserBO {

    private long id;

    private String name;

    private String mail;

    public UserBO(){
        super();
    }

    public UserBO(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public UserBO(long id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBO userBO = (UserBO) o;
        return getId() == userBO.getId() &&
                Objects.equals(getName(), userBO.getName()) &&
                Objects.equals(getMail(), userBO.getMail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMail());
    }

    @Override
    public String toString() {
        return "UserBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
