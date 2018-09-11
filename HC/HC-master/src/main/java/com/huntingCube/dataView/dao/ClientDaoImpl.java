package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Client;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dgup27 on 5/7/2017.
 */
@Repository("clientDao")
public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao {

    @Override
    public Client findById(int id) {
        Client client = getByKey(id);
        return client;
    }

    @Override
    public Client findByName(String name) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("clientName", name.toUpperCase()));
        Client client = (Client) criteria.uniqueResult();
        return client;
    }

    @Override
    public List<Client> findAllClients() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("clientName"));
        List<Client> clientList = (List<Client>) criteria.list();
        return clientList;
    }

    @Override
    public void save(Client client) {
        saveOrUpdate(client);
    }

    @Override
    public void deleteById(int id) {
        Client client = findById(id);
        client.setDeleted(true);
        saveOrUpdate(client);
    }
}
