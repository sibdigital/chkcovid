package ru.sibdigital.chkcovid.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cls_department")
public class ClsDepartment {
    private int id;
    private String name;
    private String description;
    private int statusImport;
    private Timestamp timeImport;

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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsDepartment that = (ClsDepartment) o;
        return id == that.id &&
                statusImport == that.statusImport &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(timeImport, that.timeImport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, statusImport, timeImport);
    }
}
