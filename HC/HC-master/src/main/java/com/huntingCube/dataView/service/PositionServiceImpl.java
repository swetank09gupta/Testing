package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.PositionDao;
import com.huntingCube.dataView.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dgup27 on 5/22/2017.
 */
@Service("positionService")
@Transactional
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionDao positionDao;

    @Override
    public Position findById(int id) {
        return positionDao.findById(id);
    }

    @Override
    public Position findByName(String name) {
        return positionDao.findByName(name);
    }

    @Override
    public List<Position> findAllPositions() {
        return positionDao.findAllPositions();
    }

    @Override
    public void save(Position position) {
        positionDao.save(position);
    }

    @Override
    public void updatePosition(Position position) {
        Position entity = positionDao.findById(position.getId());
        if (entity != null) {
            entity.setPositionName(position.getPositionName());
            entity.setAddedBy(position.getAddedBy());
        }
    }

    @Override
    public void deleteById(int id) {
        positionDao.deleteById(id);
    }
}
