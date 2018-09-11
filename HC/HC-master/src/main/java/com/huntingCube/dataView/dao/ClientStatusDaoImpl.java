package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ClientStatus;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guptado on 11/05/2017.
 */
@Repository("clientStatusDao")
public class ClientStatusDaoImpl extends AbstractDao<Integer, ClientStatus> implements ClientStatusDao {
    @Override
    public ClientStatus findById(int id) {
        ClientStatus clientStatus = getByKey(id);
        return clientStatus;
    }

    @Override
    public ClientStatus findByStatusName(String name) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("clientStatusName", name.toUpperCase()));
        ClientStatus clientStatus = (ClientStatus) criteria.uniqueResult();
        return clientStatus;
    }

    @Override
    public List<ClientStatus> findAllStatus() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("clientStatusName"));
        List<ClientStatus> clientStatusList = (List<ClientStatus>) criteria.list();
        return clientStatusList;
    }

    @Override
    public void save(ClientStatus clientStatus) {
        saveOrUpdate(clientStatus);
    }

    @Override
    public void deleteById(int id) {
        ClientStatus clientStatus = findById(id);
        clientStatus.setDeleted(true);
        saveOrUpdate(clientStatus);
    }
}
