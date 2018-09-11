package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Institute;

import java.util.List;

/**
 * Created by guptado on 12/01/2017.
 */
public interface InstituteDao {

    Institute findById(int id);

    Institute findByName(String name);

    List<Institute> findAllInstitutes();

    void save(Institute institute);

    void deleteById(int id);
}
