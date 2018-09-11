package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ResourceHistoryDetails;
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
@Repository("resourceHistoryDao")
public class ResourceHistoryDaoImpl extends AbstractDao<Integer, ResourceHistoryDetails> implements ResourceHistoryDao {

    static final Logger logger = LoggerFactory.getLogger(ResourceHistoryDaoImpl.class);

    @Override
    public void save(ResourceHistoryDetails resourceDetails) {
        saveOrUpdate(resourceDetails);
    }

    @Override
    public void updateResource(ResourceHistoryDetails resourceDetails) {
        update(resourceDetails);
    }

    @Override
    public ResourceHistoryDetails findById(int id) {
        return getByKey(id);
    }

    @Override
    public ResourceHistoryDetails findByEmail(String emailID) {
        if(!HuntingCubeUtility.isNotEmptyOrNull(emailID)) {
            return null;
        }

        Criteria criteria = createEntityCriteria();
        if (!emailID.isEmpty()) {
            criteria.add(Restrictions.ilike("emailId", emailID, MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("name"));
        ResourceHistoryDetails resourceHistoryDetails = (ResourceHistoryDetails)criteria.uniqueResult();
        return resourceHistoryDetails;
    }

    @Override
    public List<ResourceHistoryDetails> findResources(int maxRecords, ResourceHistoryDetails resourceDetails) {
        if (resourceDetails.getName().isEmpty() && resourceDetails.getContactNumber().isEmpty() && resourceDetails.getEmailId().isEmpty()) {
            return null;
        }
        Criteria criteria = createEntityCriteria();
        if (!resourceDetails.getName().isEmpty()) {
            criteria.add(Restrictions.ilike("name", resourceDetails.getName(), MatchMode.ANYWHERE));
        }
        if (!resourceDetails.getContactNumber().isEmpty()) {
            criteria.add(Restrictions.ilike("contactNumber", resourceDetails.getContactNumber(), MatchMode.ANYWHERE));
        }
        if (!resourceDetails.getEmailId().isEmpty()) {
            criteria.add(Restrictions.ilike("emailId", resourceDetails.getEmailId(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("name"));
        //criteria.setMaxResults(maxRecords);
        List<ResourceHistoryDetails> resourceDetailsList = (List<ResourceHistoryDetails>) criteria.list();
        return resourceDetailsList;
    }
}
