package ru.sibdigital.chkcovid.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cls_organization")
public class ClsOrganization {
    private int id;
    private String name;
    private String shortName;
    private String inn;
    private String ogrn;
    private String addressJur;
    private String okvedAdd;
    private String okved;
    private String email;
    private String phone;
    private int statusImport;
    private Timestamp timeImport;
    private String hashCode;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Basic
    @Column(name = "ogrn")
    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    @Basic
    @Column(name = "address_jur")
    public String getAddressJur() {
        return addressJur;
    }

    public void setAddressJur(String addressJur) {
        this.addressJur = addressJur;
    }

    @Basic
    @Column(name = "okved_add")
    public String getOkvedAdd() {
        return okvedAdd;
    }

    public void setOkvedAdd(String okvedAdd) {
        this.okvedAdd = okvedAdd;
    }

    @Basic
    @Column(name = "okved")
    public String getOkved() {
        return okved;
    }

    public void setOkved(String okved) {
        this.okved = okved;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "status_import")
    public int getStatusImport() {
        return statusImport;
    }

    public void setStatusImport(int statusImport) {
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
    @Column(name = "hash_code")
    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsOrganization that = (ClsOrganization) o;
        return id == that.id &&
                statusImport == that.statusImport &&
                Objects.equals(name, that.name) &&
                Objects.equals(shortName, that.shortName) &&
                Objects.equals(inn, that.inn) &&
                Objects.equals(ogrn, that.ogrn) &&
                Objects.equals(addressJur, that.addressJur) &&
                Objects.equals(okvedAdd, that.okvedAdd) &&
                Objects.equals(okved, that.okved) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(timeImport, that.timeImport) &&
                Objects.equals(hashCode, that.hashCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortName, inn, ogrn, addressJur, okvedAdd, okved, email, phone, statusImport, timeImport, hashCode);
    }
}
