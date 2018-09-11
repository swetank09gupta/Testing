package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.Institute;
import com.huntingCube.dataView.service.InstituteService;
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
public class InstituteConverter implements Converter<Object, Institute> {

    static final Logger logger = LoggerFactory.getLogger(InstituteConverter.class);

    @Autowired
    InstituteService instituteService;

    /**
     * Get Institute by Institute Id
     *
     * @param source
     * @return
     */
    @Override
    public Institute convert(Object source) {
        if(source != null && source instanceof Institute) {
            return (Institute) source;
        }
        if(source != null && source instanceof String && HuntingCubeUtility.isNotEmptyOrNull((String) source)) {
            Integer id = Integer.parseInt((String) source);
            Institute institute = instituteService.findById(id);
            logger.info("Institute : {}", institute);
            return institute;
        } else {
            return null;
        }
    }
}
