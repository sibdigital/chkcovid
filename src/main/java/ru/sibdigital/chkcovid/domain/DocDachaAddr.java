package ru.sibdigital.chkcovid.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doc_dacha_addr", schema = "public")
public class DocDachaAddr {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    private String district;
    private String address;
    @Column(name = "id_doc_dacha", nullable = false)
    private int docDachaByIdDocDacha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDocDachaByIdDocDacha() {
        return docDachaByIdDocDacha;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocDachaAddr that = (DocDachaAddr) o;
        return id == that.id &&
                Objects.equals(district, that.district) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, district, address);
    }

    public void setDocDachaByIdDocDacha(int docDachaByIdDocDacha) {
        this.docDachaByIdDocDacha = docDachaByIdDocDacha;
    }
}
