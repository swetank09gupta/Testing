package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.Institute;

import java.util.List;

/**
 * Created by dgup27 on 1/21/2017.
 */
public interface InstituteService {

    Institute findById(int id);

    Institute findByName(String name);

    List<Institute> findAllInstitutes();

    void save(Institute institute);

    void updateInstitute(Institute institute);

    void deleteById(int id);

}
