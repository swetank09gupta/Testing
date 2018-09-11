package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Program;

import java.util.List;

/**
 * Created by guptado on 12/01/2017.
 */
public interface ProgramDao {

    Program findById(int id);

    Program findByName(String name);

    List<Program> findAllPrograms();

    void save(Program program);

    void deleteById(int id);

}
