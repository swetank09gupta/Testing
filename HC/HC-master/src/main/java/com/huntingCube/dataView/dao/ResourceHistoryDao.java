package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ResourceHistoryDetails;

import java.util.List;

/**
 * Created by dgup27 on 1/10/2017.
 */
public interface ResourceHistoryDao {

    void save(ResourceHistoryDetails resourceDetails);

    void updateResource(ResourceHistoryDetails resourceDetails);

    ResourceHistoryDetails findById(int id);

    ResourceHistoryDetails findByEmail(String emailID);

    List<ResourceHistoryDetails> findResources(int maxRecords, ResourceHistoryDetails resourceHistoryDetails);
}
