package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.Position;
import com.huntingCube.dataView.service.PositionService;
import com.huntingCube.utility.HuntingCubeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by dgup27 on 5/22/2017.
 */
@Component
public class PositionConverter implements Converter<Object, Position> {

    @Autowired
    PositionService positionService;

    @Override
    public Position convert(Object source) {
        if (source != null && source instanceof Position) {
            return (Position) source;
        }
        if (source != null && source instanceof String && HuntingCubeUtility.isNotEmptyOrNull((String) source)) {
            Integer id = Integer.parseInt((String) source);
            Position position = positionService.findById(id);
            return position;
        } else {
            return null;
        }
    }
}
