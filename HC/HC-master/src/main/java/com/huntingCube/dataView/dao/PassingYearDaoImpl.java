package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.PassingYear;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guptado on 12/01/2017.
 */
@Repository("passingYearDao")
public class PassingYearDaoImpl extends AbstractDao<Integer, PassingYear> implements PassingYearDao {
    @Override
    public PassingYear findById(int id) {
        PassingYear passingYear = getByKey(id);
        return passingYear;
    }

    @Override
    public PassingYear findByName(String name) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("passingYear", name.toUpperCase()));
        return (PassingYear)criteria.uniqueResult();
    }

    @Override
    public List<PassingYear> findAllPassingYears() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("passingYear"));
        List<PassingYear> passingYearList = (List<PassingYear>) criteria.list();
        return passingYearList;
    }

    @Override
    public void save(PassingYear passingYear) {
        persist(passingYear);
    }

    @Override
    public void deleteById(int id) {
        PassingYear passingYear = findById(id);
        passingYear.setDeleted(true);
        saveOrUpdate(passingYear);
    }
}
