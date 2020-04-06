package ru.sibdigital.chkcovid.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doc_person")
public class DocPerson {
    private int id;
    private int idRequest;
    private String lastname;
    private String firstname;
    private String patronymic;
    private boolean isAgree;
    private String shortName;
    private String inn;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_request")
    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "is_agree")
    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }

    @Basic
    @Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "inn")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocPerson docPerson = (DocPerson) o;
        return id == docPerson.id &&
                idRequest == docPerson.idRequest &&
                isAgree == docPerson.isAgree &&
                Objects.equals(lastname, docPerson.lastname) &&
                Objects.equals(firstname, docPerson.firstname) &&
                Objects.equals(patronymic, docPerson.patronymic) &&
                Objects.equals(shortName, docPerson.shortName) &&
                Objects.equals(inn, docPerson.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRequest, lastname, firstname, patronymic, isAgree, shortName, inn);
    }
}
