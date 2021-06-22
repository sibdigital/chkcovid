package ru.sibdigital.chkcovid.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "v_doc_person_and_org_info")
public class DocPerson {
    private int id;
    private Long idRequest;
    private String lastname;
    private String firstname;
    private String patronymic;
    private Integer statusImport;
    private Timestamp timeImport;
    private String shortName;
    private String inn;

    //private transient LocalDate dateActual;

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
    public Long getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Long idRequest) {
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
    @Column(name = "status_import")
    public Integer getStatusImport() {
        return statusImport;
    }

    public void setStatusImport(Integer statusImport) {
        this.statusImport = statusImport;
    }

    @Basic
    @Column(name = "time_import")
    public Timestamp getTimeImport() {
        return timeImport;
    }

    public void setTimeImport(Timestamp timeImport) {
        this.timeImport = timeImport;
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

//    @Transient
//    public LocalDate getDateActual() {
//        return dateActual;
//    }
//
//    public void setDateActual(LocalDate dateActual) {
//        this.dateActual = dateActual;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocPerson docPerson = (DocPerson) o;
        return id == docPerson.id &&
                idRequest == docPerson.idRequest &&
                Objects.equals(lastname, docPerson.lastname) &&
                Objects.equals(firstname, docPerson.firstname) &&
                Objects.equals(patronymic, docPerson.patronymic) &&
                Objects.equals(statusImport, docPerson.statusImport) &&
                Objects.equals(timeImport, docPerson.timeImport) &&
                Objects.equals(shortName, docPerson.shortName) &&
                Objects.equals(inn, docPerson.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idRequest, lastname, firstname, patronymic, statusImport, timeImport, shortName, inn);
    }
}
