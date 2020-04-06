package ru.sibdigital.chkcovid.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doc_address_fact")
public class DocAddressFact {
    private int id;
    private String addressFact;
    private int personOfficeFactCnt;
    private int idRequest;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "address_fact")
    public String getAddressFact() {
        return addressFact;
    }

    public void setAddressFact(String addressFact) {
        this.addressFact = addressFact;
    }

    @Basic
    @Column(name = "person_office_fact_cnt")
    public int getPersonOfficeFactCnt() {
        return personOfficeFactCnt;
    }

    public void setPersonOfficeFactCnt(int personOfficeFactCnt) {
        this.personOfficeFactCnt = personOfficeFactCnt;
    }

    @Basic
    @Column(name = "id_request")
    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocAddressFact that = (DocAddressFact) o;
        return id == that.id &&
                personOfficeFactCnt == that.personOfficeFactCnt &&
                idRequest == that.idRequest &&
                Objects.equals(addressFact, that.addressFact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressFact, personOfficeFactCnt, idRequest);
    }
}
