package com.huntingCube.dataView.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dgup27 on 1/8/2017.
 */
@Entity
@Table(name = "RESOURCE_DETAILS_HISTORY")
public class ResourceHistoryDetails extends ResourceBase implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESOURCE_HISTORY_ID")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
