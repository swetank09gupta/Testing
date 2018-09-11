package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.*;
import com.huntingCube.dataView.model.*;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by dgup27 on 1/10/2017.
 */
@Service("resourceHistoryService")
@Transactional
public class ResourceHistoryServiceImpl implements ResourceHistoryService {

    static final Logger logger = LoggerFactory.getLogger(ResourceHistoryServiceImpl.class);

    @Autowired
    ResourceHistoryDao resourceDao;

    @Autowired
    InstituteDao instituteDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    PassingYearDao passingYearDao;

    @Autowired
    ProgramDao programDao;

    @Autowired
    StreamDao streamDao;

    @Override
    public List<ResourceHistoryDetails> findResources(int maxRecords, ResourceHistoryDetails resourceDetails) {
        return resourceDao.findResources(maxRecords, resourceDetails);
    }

    @Override
    public ResourceHistoryDetails findById(int id) {
        return resourceDao.findById(id);
    }

    @Override
    public ResourceHistoryDetails findByEmail(String emailID) {
        return resourceDao.findByEmail(emailID);
    }

    @Override
    public void save(ResourceHistoryDetails resourceDetails) {
        /*ResourceDetails resourceByEmail = findByEmail(resourceDetails.getEmailId());
        resourceByEmail = resourceDao.findById(resourceByEmail.getId());
        if(resourceByEmail != null) {
            try {
                
            } catch (Exception e) {
                logger.error("Error while updating resource", e);
            }
        } else {
            resourceDao.save(resourceDetails);
        }*/
        logger.info("resourceDetails>>>>>>>>>>>"+resourceDetails);
        resourceDao.save(resourceDetails);
    }

    @Override
    public void update(ResourceHistoryDetails resourceDetails) {
        ResourceHistoryDetails entity = resourceDao.findById(resourceDetails.getId());
        if (entity != null) {
            entity = resourceDetails;
        }
    }
}
