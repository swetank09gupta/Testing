package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Institute;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guptado on 12/01/2017.
 */
@Repository("instituteDao")
public class InstituteDaoImpl extends AbstractDao<Integer, Institute> implements InstituteDao {

    @Override
    public Institute findById(int id) {
        Institute institute = getByKey(id);
        return institute;
    }

    @Override
    public Institute findByName(String name) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("instituteName", name.toUpperCase()));
        Institute institute = (Institute) criteria.uniqueResult();
        return institute;
    }

    @Override
    public List<Institute> findAllInstitutes() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("instituteName"));
        List<Institute> instituteList = (List<Institute>) criteria.list();
        return instituteList;
    }

    @Override
    public void save(Institute institute) {
        persist(institute);
    }

    @Override
    public void deleteById(int id) {
        Institute institute = findById(id);
        institute.setDeleted(true);
        saveOrUpdate(institute);
    }
}
