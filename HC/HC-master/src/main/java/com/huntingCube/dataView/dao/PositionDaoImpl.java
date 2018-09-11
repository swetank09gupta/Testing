package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Position;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dgup27 on 5/22/2017.
 */
@Repository("positionDao")
public class PositionDaoImpl extends AbstractDao<Integer, Position> implements PositionDao {


    @Override
    public Position findById(int id) {
        Position position = getByKey(id);
        return position;
    }

    @Override
    public Position findByName(String name) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("positionName", name.toUpperCase()));
        return (Position) criteria.uniqueResult();
    }

    @Override
    public List<Position> findAllPositions() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("positionName"));
        List<Position> positionList = (List<Position>) criteria.list();
        return positionList;
    }

    @Override
    public void save(Position position) {
        persist(position);
    }

    @Override
    public void deleteById(int id) {
        Position position = findById(id);
        position.setDeleted(true);
        saveOrUpdate(position);
    }
}
