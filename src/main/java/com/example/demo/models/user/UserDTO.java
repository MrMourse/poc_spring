package com.example.demo.models.user;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "user")
public class UserDTO implements Serializable {
    private long id;

    private String name;

    private String mail;

    public UserDTO(){
        super();
    }

    public UserDTO(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

    public UserDTO(long id, String name, String mail) {
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
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }
    @XmlElement
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
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    public void setIdUpdate(Long id){
        if (this.getId() != id){
            this.setId(id);
        }
    }
}
