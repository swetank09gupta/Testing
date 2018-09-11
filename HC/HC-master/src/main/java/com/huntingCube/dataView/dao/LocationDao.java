package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Location;

import java.util.List;

/**
 * Created by guptado on 12/01/2017.
 */
public interface LocationDao {

    Location findById(int id);

    Location findByName(String name);

    List<Location> findAllLocations();

    void save(Location location);

    void deleteById(int id);
}
