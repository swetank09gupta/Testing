package com.huntingCube.dataView.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dgup27 on 5/6/2017.
 */
@Entity
@Table(name = "CLIENT_STATUS")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClientStatus extends BaseEntity implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_STATUS_ID")
    private int id;

    @NotEmpty
    @Column(name = "CLIENT_STATUS_NAME", nullable = false)
    private String clientStatusName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientStatusName() {
        return clientStatusName;
    }

    public void setClientStatusName(String clientStatusName) {
        this.clientStatusName = clientStatusName;
    }

    @Override
    public String toString() {
        return "ClientStatus{" +
                "id=" + id +
                ", clientStatusName='" + clientStatusName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientStatus that = (ClientStatus) o;

        return clientStatusName.equals(that.clientStatusName);
    }

    @Override
    public int hashCode() {
        return clientStatusName.hashCode();
    }
}
