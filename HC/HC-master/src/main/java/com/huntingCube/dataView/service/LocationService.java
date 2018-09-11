package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.Location;

import java.util.List;

/**
 * Created by dgup27 on 1/21/2017.
 */
public interface LocationService {

    Location findById(int id);

    Location findByName(String name);

    List<Location> findAllLocations();

    void save(Location location);

    void updateLocation(Location location);

    void deleteById(int id);
}
