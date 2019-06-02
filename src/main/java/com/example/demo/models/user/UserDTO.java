package com.example.demo.models.user;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement(name = "user")
public class UserDTO extends UserBO implements Serializable  {

    @Override
    @XmlElement
    public void setName(String name) {
        super.name = name;
    }

    @Override
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
}
