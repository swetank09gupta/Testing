package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Program;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guptado on 12/01/2017.
 */
@Repository("programDao")
public class ProgramDaoImpl extends AbstractDao<Integer, Program> implements ProgramDao {


    @Override
    public Program findById(int id) {
        Program program = getByKey(id);
        return program;
    }

    @Override
    public Program findByName(String name) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("programName", name.toUpperCase()));
        return (Program) criteria.uniqueResult();
    }

    @Override
    public List<Program> findAllPrograms() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("programName"));
        List<Program> programList = (List<Program>) criteria.list();
        return programList;
    }

    @Override
    public void save(Program program) {
        persist(program);
    }

    @Override
    public void deleteById(int id) {
        Program program = findById(id);
        program.setDeleted(true);
        saveOrUpdate(program);
    }
}
