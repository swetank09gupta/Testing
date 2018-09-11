package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ResourceDetails;
import com.huntingCube.global.resources.dao.AbstractDao;
import com.huntingCube.utility.HuntingCubeUtility;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dgup27 on 1/10/2017.
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends AbstractDao<Integer, ResourceDetails> implements ResourceDao {

    static final Logger logger = LoggerFactory.getLogger(ResourceDaoImpl.class);

    @Override
    public void save(ResourceDetails resourceDetails) {
        saveOrUpdate(resourceDetails);
    }

    @Override
    public void updateResource(ResourceDetails resourceDetails) {
        update(resourceDetails);
    }

    @Override
    public ResourceDetails findById(int id) {
        return getByKey(id);
    }

    @Override
    public ResourceDetails findByEmail(String emailID) {
        if(!HuntingCubeUtility.isNotEmptyOrNull(emailID)) {
            return null;
        }

        Criteria criteria = createEntityCriteria();
        if (!emailID.isEmpty()) {
            criteria.add(Restrictions.ilike("emailId", emailID, MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("name"));
        ResourceDetails resourceDetails = (ResourceDetails)criteria.uniqueResult();
        return resourceDetails;
    }

    @Override
    public void deleteById(int id) {
        ResourceDetails resourceDetails = findById(id);
        resourceDetails.setDeleted(true);
        saveOrUpdate(resourceDetails);
    }

    @Override
    public List<ResourceDetails> findResources(int maxRecords, ResourceDetails resourceDetails) {
        boolean anySearchCriteria = false;
        Criteria criteria = createEntityCriteria();
        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getName())) {
            anySearchCriteria = true;
            criteria.add(Restrictions.ilike("name", resourceDetails.getName(), MatchMode.ANYWHERE));
        }
        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getContactNumber())) {
            anySearchCriteria = true;
            criteria.add(Restrictions.ilike("contactNumber", resourceDetails.getContactNumber(), MatchMode.ANYWHERE));
        }
        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getEmailId())) {
            anySearchCriteria = true;
            criteria.add(Restrictions.ilike("emailId", resourceDetails.getEmailId(), MatchMode.ANYWHERE));
        }
        if(resourceDetails.getInstitute() != null) {
            anySearchCriteria = true;
            criteria.add((Restrictions.eq("institute", resourceDetails.getInstitute())));
        }
        if(resourceDetails.getStream() != null) {
            anySearchCriteria = true;
            criteria.add((Restrictions.eq("stream", resourceDetails.getStream())));
        }
        if(resourceDetails.getProgram() != null) {
            anySearchCriteria = true;
            criteria.add((Restrictions.eq("program", resourceDetails.getProgram())));
        }
        if(resourceDetails.getPassingYear() != null) {
            anySearchCriteria = true;
            criteria.add((Restrictions.eq("passingYear", resourceDetails.getPassingYear())));
        }
        if(resourceDetails.getNoticePeriod() != 0.0) {
            anySearchCriteria = true;
            criteria.add((Restrictions.le("noticePeriod", resourceDetails.getNoticePeriod())));
        }
        if(resourceDetails.getExperience() != 0.0) {
            anySearchCriteria = true;
            criteria.add((Restrictions.ge("experience", resourceDetails.getExperience())));
        }
        if(resourceDetails.getFixedCTC() != 0.0) {
            anySearchCriteria = true;
            criteria.add((Restrictions.le("fixedCTC", resourceDetails.getFixedCTC())));
        }
        if(resourceDetails.getExpectedCTC() != 0.0) {
            anySearchCriteria = true;
            criteria.add((Restrictions.le("expectedCTC", resourceDetails.getExpectedCTC())));
        }
        if(resourceDetails.getVariableCTC() != 0.0) {
            anySearchCriteria = true;
            criteria.add((Restrictions.le("variableCTC", resourceDetails.getVariableCTC())));
        }

        if (!anySearchCriteria) {
            return null;
        }
        criteria.addOrder(Order.asc("name"));
        //criteria.setMaxResults(maxRecords);
        List<ResourceDetails> resourceDetailsList = (List<ResourceDetails>) criteria.list();
        return resourceDetailsList;
    }
}
