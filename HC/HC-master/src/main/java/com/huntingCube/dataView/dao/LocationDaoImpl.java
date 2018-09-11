package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Location;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guptado on 12/01/2017.
 */
@Repository("locationDao")
public class LocationDaoImpl extends AbstractDao<Integer, Location> implements LocationDao {

    static final Logger logger = LoggerFactory.getLogger(LocationDaoImpl.class);

    @Override
    public Location findById(int id) {
        Location location = getByKey(id);
        return location;
    }

    @Override
    public Location findByName(String name) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("locationName", name.toUpperCase()));
        Location location = (Location) criteria.uniqueResult();
        return location;
    }

    @Override
    public List<Location> findAllLocations() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("locationName"));
        List<Location> locationList = (List<Location>) criteria.list();
        return locationList;
    }

    @Override
    public void save(Location location) {
        logger.info("In save location function************************");
        persist(location);
    }

    @Override
    public void deleteById(int id) {
        Location location = findById(id);
        location.setDeleted(true);
        saveOrUpdate(location);
    }
}
