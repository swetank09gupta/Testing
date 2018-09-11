package com.huntingCube.dataView.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dgup27 on 1/8/2017.
 */
@Entity
@Table(name = "LOCATION")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Location extends BaseEntity implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID")
    private int id;

    @NotEmpty
    @Column(name = "LOCATION_NAME", nullable = false)
    private String locationName;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", locationName='" + locationName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return locationName.equals(location.locationName);
    }

    @Override
    public int hashCode() {
        return locationName.hashCode();
    }
}
