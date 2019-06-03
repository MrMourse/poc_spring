package com.example.demo.models.user;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

/**
 * Data Transfert Objet user, il fait le lien avec le service extérieur.
 */
@XmlRootElement(name = "user")
public class UserDTO implements Serializable {

    public static final long serialVersionUID = -8259266566722741094L;

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
