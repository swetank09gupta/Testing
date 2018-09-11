package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.PassingYear;

import java.util.List;

/**
 * Created by dgup27 on 1/21/2017.
 */
public interface PassingYearService {

    PassingYear findById(int id);

    PassingYear findByName(String name);

    List<PassingYear> findAllPassingYears();

    void save(PassingYear passingYear);

    void updatePassingYear(PassingYear passingYear);

    void deleteById(int id);
}
