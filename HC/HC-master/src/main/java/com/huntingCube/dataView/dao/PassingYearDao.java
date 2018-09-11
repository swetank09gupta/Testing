package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.PassingYear;

import java.util.List;

/**
 * Created by guptado on 12/01/2017.
 */
public interface PassingYearDao {

    PassingYear findById(int id);

    PassingYear findByName(String name);

    List<PassingYear> findAllPassingYears();

    void save(PassingYear passingYear);

    void deleteById(int id);

}
