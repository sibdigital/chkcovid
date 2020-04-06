package ru.sibdigital.chkcovid.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "doc_request")
public class DocRequest {
    private int id;
    private int personOfficeCnt;
    private int personRemoteCnt;
    private int personSlrySaveCnt;
    private int personOfficeFactCnt;
    private int idOrganization;
    private int idDepartment;
    private String attachmentPath;
    private int statusReview;
    private Timestamp timeCreate;
    private int statusImport;
    private Timestamp timeImport;
    private Timestamp timeReview;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "person_office_cnt")
    public int getPersonOfficeCnt() {
        return personOfficeCnt;
    }

    public void setPersonOfficeCnt(int personOfficeCnt) {
        this.personOfficeCnt = personOfficeCnt;
    }

    @Basic
    @Column(name = "person_remote_cnt")
    public int getPersonRemoteCnt() {
        return personRemoteCnt;
    }

    public void setPersonRemoteCnt(int personRemoteCnt) {
        this.personRemoteCnt = personRemoteCnt;
    }

    @Basic
    @Column(name = "person_slry_save_cnt")
    public int getPersonSlrySaveCnt() {
        return personSlrySaveCnt;
    }

    public void setPersonSlrySaveCnt(int personSlrySaveCnt) {
        this.personSlrySaveCnt = personSlrySaveCnt;
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
    @Column(name = "id_organization")
    public int getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(int idOrganization) {
        this.idOrganization = idOrganization;
    }

    @Basic
    @Column(name = "id_department")
    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Basic
    @Column(name = "attachment_path")
    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Basic
    @Column(name = "status_review")
    public int getStatusReview() {
        return statusReview;
    }

    public void setStatusReview(int statusReview) {
        this.statusReview = statusReview;
    }

    @Basic
    @Column(name = "time_create")
    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
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
    @Column(name = "time_review")
    public Timestamp getTimeReview() {
        return timeReview;
    }

    public void setTimeReview(Timestamp timeReview) {
        this.timeReview = timeReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocRequest that = (DocRequest) o;
        return id == that.id &&
                personOfficeCnt == that.personOfficeCnt &&
                personRemoteCnt == that.personRemoteCnt &&
                personSlrySaveCnt == that.personSlrySaveCnt &&
                personOfficeFactCnt == that.personOfficeFactCnt &&
                idOrganization == that.idOrganization &&
                idDepartment == that.idDepartment &&
                statusReview == that.statusReview &&
                statusImport == that.statusImport &&
                Objects.equals(attachmentPath, that.attachmentPath) &&
                Objects.equals(timeCreate, that.timeCreate) &&
                Objects.equals(timeImport, that.timeImport) &&
                Objects.equals(timeReview, that.timeReview);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personOfficeCnt, personRemoteCnt, personSlrySaveCnt, personOfficeFactCnt, idOrganization, idDepartment, attachmentPath, statusReview, timeCreate, statusImport, timeImport, timeReview);
    }
}
