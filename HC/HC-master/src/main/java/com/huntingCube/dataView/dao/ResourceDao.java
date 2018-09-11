package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ResourceDetails;

import java.util.List;

/**
 * Created by dgup27 on 1/10/2017.
 */
public interface ResourceDao {

    void save(ResourceDetails resourceDetails);

    void updateResource(ResourceDetails resourceDetails);

    ResourceDetails findById(int id);

    ResourceDetails findByEmail(String emailID);

    List<ResourceDetails> findResources(int maxRecords, ResourceDetails resourceDetails);

    void deleteById(int id);
}
