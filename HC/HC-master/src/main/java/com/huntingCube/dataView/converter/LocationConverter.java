package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.Location;
import com.huntingCube.dataView.service.LocationService;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by dgup27 on 1/23/2017.
 */
@Component
public class LocationConverter implements Converter<Object, Location> {

    static final Logger logger = LoggerFactory.getLogger(LocationConverter.class);

    @Autowired
    LocationService locationService;

    @Override
    public Location convert(Object source) {
        if(source != null && source instanceof Location) {
            return (Location) source;
        }
        if(source != null && source instanceof String && HuntingCubeUtility.isNotEmptyOrNull((String) source)) {
            Integer id = Integer.parseInt((String) source);
            Location location = locationService.findById(id);
            return location;
        } else {
            return null;
        }
    }
}
