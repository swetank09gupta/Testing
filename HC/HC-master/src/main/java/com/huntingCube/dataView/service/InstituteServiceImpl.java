package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.InstituteDao;
import com.huntingCube.dataView.model.Institute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dgup27 on 1/21/2017.
 */
@Service("instituteService")
@Transactional
public class InstituteServiceImpl implements InstituteService {

    @Autowired
    InstituteDao instituteDao;

    @Override
    public Institute findById(int id) {
        return instituteDao.findById(id);
    }

    @Override
    public Institute findByName(String name) {
        return instituteDao.findByName(name);
    }

    @Override
    public List<Institute> findAllInstitutes() {
        return instituteDao.findAllInstitutes();
    }

    @Override
    public void save(Institute institute) {
        instituteDao.save(institute);
    }

    @Override
    public void updateInstitute(Institute institute) {
        Institute entity = instituteDao.findById(institute.getId());
        if(entity != null) {
            entity.setInstituteName(institute.getInstituteName());
            entity.setAddedBy(institute.getAddedBy());
        }
    }

    @Override
    public void deleteById(int id) {
        instituteDao.deleteById(id);
    }
}
