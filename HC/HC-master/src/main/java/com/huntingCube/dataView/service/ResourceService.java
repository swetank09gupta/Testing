package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.ResourceDetails;

import java.util.List;
import java.util.Map;

/**
 * Created by dgup27 on 1/10/2017.
 */

public interface ResourceService {

    List<ResourceDetails> findResources(int maxRecords, ResourceDetails resourceDetails);

    ResourceDetails findById(int id);

    ResourceDetails findByEmail(String emailID);

    void save(ResourceDetails resourceDetails);

    void update(ResourceDetails resourceDetails);

    Map<String, String> saveExcelRecords(String filePath);

    void deleteById(int id);
}
