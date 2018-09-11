package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.Stream;
import com.huntingCube.dataView.service.StreamService;
import com.huntingCube.utility.HuntingCubeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by dgup27 on 1/23/2017.
 */
@Component
public class StreamConverter implements Converter<Object, Stream> {

    @Autowired
    StreamService streamService;

    @Override
    public Stream convert(Object source) {
        if(source != null && source instanceof Stream) {
            return (Stream) source;
        }
        if(source != null && source instanceof String && HuntingCubeUtility.isNotEmptyOrNull((String) source)) {
            Integer id = Integer.parseInt((String) source);
            Stream stream = streamService.findById(id);
            return stream;
        } else {
            return null;
        }
    }
}
