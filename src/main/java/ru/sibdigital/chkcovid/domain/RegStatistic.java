package ru.sibdigital.chkcovid.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reg_statistic")
public class RegStatistic {
    private int id;
    private String lastname;
    private String firstname;
    private String patronymic;
    private String inn;
    private Timestamp reg_time;
    private int results;
    private String additional_info;

    public RegStatistic(String lastname, String firstname, String patronymic, String inn, int results) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.inn = inn;
        this.results = results;
        this.reg_time = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "inn")
    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Basic
    @Column(name = "reg_time")
    public Timestamp getTime() {
        return reg_time;
    }

    public void setTime(Timestamp reg_time) {
        this.reg_time = reg_time;
    }

    @Basic
    @Column(name = "results")
    public int getResults( ){ return results; }

    public void setResults(int results) { this.results = results; }

    @Basic
    @Column(name = "additional_info")
    public String getAdditionalInfo() {
        return additional_info;
    }

    public void setAdditionalInfo(String additional_info) {
        this.additional_info = additional_info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegStatistic regStatistic = (RegStatistic) o;
        return id == regStatistic.id &&
                Objects.equals(lastname, regStatistic.lastname) &&
                Objects.equals(firstname, regStatistic.firstname) &&
                Objects.equals(patronymic, regStatistic.patronymic) &&
                Objects.equals(inn, regStatistic.inn) &&
                Objects.equals(reg_time, regStatistic.reg_time) &&
                Objects.equals(results, regStatistic.results) &&
                Objects.equals(additional_info, regStatistic.additional_info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, patronymic, reg_time, inn, results);
    }
}