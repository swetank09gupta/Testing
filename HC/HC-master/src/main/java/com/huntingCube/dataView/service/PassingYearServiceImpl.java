package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.PassingYearDao;
import com.huntingCube.dataView.model.PassingYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dgup27 on 1/21/2017.
 */
@Service("passingYearService")
@Transactional
public class PassingYearServiceImpl implements PassingYearService {

    @Autowired
    PassingYearDao passingYearDao;

    @Override
    public PassingYear findById(int id) {
        return passingYearDao.findById(id);
    }

    @Override
    public PassingYear findByName(String name) {
        return passingYearDao.findByName(name);
    }

    @Override
    public List<PassingYear> findAllPassingYears() {
        return passingYearDao.findAllPassingYears();
    }

    @Override
    public void save(PassingYear passingYear) {
        passingYearDao.save(passingYear);
    }

    @Override
    public void updatePassingYear(PassingYear passingYear) {
        PassingYear entity = passingYearDao.findById(passingYear.getId());
        if(entity != null) {
            entity.setPassingYear(passingYear.getPassingYear());
            entity.setAddedBy(passingYear.getAddedBy());
        }
    }

    @Override
    public void deleteById(int id) {
        passingYearDao.deleteById(id);
    }
}
