package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.ProgramDao;
import com.huntingCube.dataView.model.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dgup27 on 1/21/2017.
 */
@Service("programService")
@Transactional
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    ProgramDao programDao;

    @Override
    public Program findById(int id) {
        return programDao.findById(id);
    }

    @Override
    public Program findByName(String name) {
        return programDao.findByName(name);
    }

    @Override
    public List<Program> findAllPrograms() {
        return programDao.findAllPrograms();
    }

    @Override
    public void save(Program program) {
        programDao.save(program);
    }

    @Override
    public void updateProgram(Program program) {
        Program entity = programDao.findById(program.getId());
        if(entity != null) {
            entity.setProgramName(program.getProgramName());
            entity.setAddedBy(program.getAddedBy());
        }
    }

    @Override
    public void deleteById(int id) {
        programDao.deleteById(id);
    }
}
