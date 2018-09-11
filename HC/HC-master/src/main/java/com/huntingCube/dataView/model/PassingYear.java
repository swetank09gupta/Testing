package com.huntingCube.dataView.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dgup27 on 1/8/2017.
 */
@Entity
@Table(name = "PASSING_YEAR")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PassingYear extends BaseEntity implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PASSING_YEAR_ID")
    private int id;

    @NotEmpty
    @Column(name = "PASSING_YEAR", nullable = false)
    private String passingYear;

    public String getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PassingYear{" +
                "id=" + id +
                ", passingYear='" + passingYear + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassingYear that = (PassingYear) o;

        return passingYear.equals(that.passingYear);
    }

    @Override
    public int hashCode() {
        return passingYear.hashCode();
    }
}
