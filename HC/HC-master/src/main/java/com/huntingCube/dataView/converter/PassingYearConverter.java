package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.PassingYear;
import com.huntingCube.dataView.service.PassingYearService;
import com.huntingCube.utility.HuntingCubeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by dgup27 on 1/23/2017.
 */
@Component
public class PassingYearConverter implements Converter<Object, PassingYear> {

    @Autowired
    PassingYearService passingYearService;

    @Override
    public PassingYear convert(Object source) {
        if(source != null && source instanceof PassingYear) {
            return (PassingYear) source;
        }
        if(source != null && source instanceof String && HuntingCubeUtility.isNotEmptyOrNull((String) source)) {
            Integer id = Integer.parseInt((String) source);
            PassingYear passingYear = passingYearService.findById(id);
            return passingYear;
        } else {
            return null;
        }
    }
}
