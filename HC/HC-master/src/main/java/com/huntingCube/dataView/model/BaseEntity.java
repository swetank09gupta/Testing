package com.huntingCube.dataView.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by dgup27 on 1/8/2017.
 */
@MappedSuperclass
public abstract class BaseEntity {

    @NotEmpty
    @Column(name = "ADDED_BY", nullable = false)
    private String addedBy;

    @Column(name = "IS_DELETED", nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
