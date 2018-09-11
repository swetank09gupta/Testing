package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.Position;

import java.util.List;

/**
 * Created by dgup27 on 5/22/2017.
 */
public interface PositionService {

    Position findById(int id);

    Position findByName(String name);

    List<Position> findAllPositions();

    void save(Position position);

    void updatePosition(Position position);

    void deleteById(int id);
}
