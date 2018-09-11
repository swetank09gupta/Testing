package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.Program;

import java.util.List;

/**
 * Created by dgup27 on 1/21/2017.
 */
public interface ProgramService {

    Program findById(int id);

    Program findByName(String name);

    List<Program> findAllPrograms();

    void save(Program program);

    void updateProgram(Program program);

    void deleteById(int id);
}
