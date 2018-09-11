package com.huntingCube.dataView.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dgup27 on 5/6/2017.
 */
@Entity
@Table(name = "CLIENT_HISTORY" , indexes = {
        @Index(columnList = "ADDED_BY", name = "addedBy_index")
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClientHistory extends BaseEntity implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_HISTORY_ID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_STATUS_ID")
    private ClientStatus clientStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "POSITION_ID")
    private Position positionName;

    @NotNull
    @Column(name = "RESOURCE_ID", nullable = false)
    private int resourceID;

    @NotNull
    @Column(name = "RESOURCE_HISTORY_ID", nullable = false)
    private int resourceHistoryID;

    @Column(name = "ADDED_DATE")
    private Date addedDate;

    @Column(name = "CLIENT_REMARKS", length = 65535)
    private String remarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public int getResourceHistoryID() {
        return resourceHistoryID;
    }

    public void setResourceHistoryID(int resourceHistoryID) {
        this.resourceHistoryID = resourceHistoryID;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Position getPositionName() {
        return positionName;
    }

    public void setPositionName(Position positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "ClientHistory{" +
                "id=" + id +
                ", client=" + client +
                ", clientStatus=" + clientStatus +
                ", positionName=" + positionName +
                ", resourceID=" + resourceID +
                ", resourceHistoryID=" + resourceHistoryID +
                ", addedDate=" + addedDate +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
