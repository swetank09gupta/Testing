package com.huntingCube.dataView.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dgup27 on 1/8/2017.
 */
@Entity
@Table(name = "INSTITUTE")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Institute extends BaseEntity implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSTITUTE_ID")
    private int id;

    @NotEmpty
    @Column(name = "INSTITUTE_NAME", nullable = false)
    private String instituteName;

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Institute{" +
                "id=" + id +
                ", instituteName='" + instituteName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institute institute = (Institute) o;

        return instituteName.equals(institute.instituteName);
    }

    @Override
    public int hashCode() {
        return instituteName.hashCode();
    }
}
