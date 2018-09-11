package com.huntingCube.dataView.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dgup27 on 1/8/2017.
 */
@Entity
@Table(name = "PROGRAM")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Program extends BaseEntity implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROGRAM_ID")
    private int id;

    @NotEmpty
    @Column(name = "PROGRAM_NAME", nullable = false)
    private String programName;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", programName='" + programName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Program program = (Program) o;

        return programName.equals(program.programName);
    }

    @Override
    public int hashCode() {
        return programName.hashCode();
    }
}
