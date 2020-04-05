package ru.sibdigital.chkcovid.domain;

import lombok.Builder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(OrganizationPK.class)
@Builder(toBuilder = true)
public class Organization {
    private String itn;
    private String organizationName;
    private String lastname;
    private String firstname;
    private String patronymic;


    public Organization() {
    }

    public Organization(String itn, String organizationName, String lastname, String firstname, String patronymic) {
        this.itn = itn;
        this.organizationName = organizationName;
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
    }

    @Id
    @Column(name = "itn")
    public String getItn() {
        return itn;
    }

    public void setItn(String itn) {
        this.itn = itn;
    }

    @Basic
    @Column(name = "organization_name")
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Id
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(itn, that.itn) &&
                Objects.equals(organizationName, that.organizationName) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(patronymic, that.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itn, organizationName, lastname, firstname, patronymic);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "itn='" + itn + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                '}';
    }
}
