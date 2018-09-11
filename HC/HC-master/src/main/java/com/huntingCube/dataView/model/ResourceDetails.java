package com.huntingCube.dataView.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dgup27 on 1/8/2017.
 */
@Entity
@Table(name = "RESOURCE_DETAILS" , indexes = {
        @Index(columnList = "NAME", name = "name_index"),
        @Index(columnList = "CONTACT_NUMBER", name = "contactNumber_index"),
        @Index(columnList = "EMAIL", name = "emailId_index"),
        @Index(columnList = "ADDED_BY", name = "addedBy_index")
})
public class ResourceDetails extends ResourceBase implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESOURCE_ID")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
