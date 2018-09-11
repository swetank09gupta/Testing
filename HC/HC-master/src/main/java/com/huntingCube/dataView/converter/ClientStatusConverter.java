package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.ClientStatus;
import com.huntingCube.dataView.service.ClientStatusService;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by guptado on 11/05/2017.
 */
@Component
public class ClientStatusConverter implements Converter<Object, ClientStatus> {

    static final Logger logger = LoggerFactory.getLogger(ClientStatusConverter.class);

    @Autowired
    ClientStatusService clientStatusService;

    @Override
    public ClientStatus convert(Object source) {
        if(source != null && source instanceof ClientStatus) {
            return (ClientStatus) source;
        }
        if(source != null && source instanceof String && HuntingCubeUtility.isNotEmptyOrNull((String) source)) {
            Integer id = Integer.parseInt((String) source);
            ClientStatus clientStatus = clientStatusService.findById(id);
            logger.info("Candidate Status : {}", clientStatus);
            return clientStatus;
        } else {
            return null;
        }
    }
}
