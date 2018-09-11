package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.ResourceDetails;
import com.huntingCube.dataView.model.ResourceHistoryDetails;

import java.util.List;

/**
 * Created by dgup27 on 1/10/2017.
 */

public interface ResourceHistoryService {

    List<ResourceHistoryDetails> findResources(int maxRecords, ResourceHistoryDetails resourceDetails);

    ResourceHistoryDetails findById(int id);

    ResourceHistoryDetails findByEmail(String emailID);

    void save(ResourceHistoryDetails resourceDetails);

    void update(ResourceHistoryDetails resourceDetails);

}
