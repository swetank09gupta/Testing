package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.LocationDao;
import com.huntingCube.dataView.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dgup27 on 1/21/2017.
 */
@Service("locationService")
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationDao locationDao;

    @Override
    public Location findById(int id) {
        return locationDao.findById(id);
    }

    @Override
    public Location findByName(String name) {
        return locationDao.findByName(name);
    }

    @Override
    public List<Location> findAllLocations() {
        return locationDao.findAllLocations();
    }

    @Override
    public void save(Location location) {
        locationDao.save(location);
    }

    @Override
    public void updateLocation(Location location) {
        Location entity = locationDao.findById(location.getId());
        if(entity != null) {
            entity.setLocationName(location.getLocationName());
            entity.setAddedBy(location.getAddedBy());
        }
    }

    @Override
    public void deleteById(int id) {
        locationDao.deleteById(id);
    }
}
