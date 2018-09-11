package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.ClientDao;
import com.huntingCube.dataView.model.Client;
import com.huntingCube.dataView.model.ClientHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dgup27 on 5/7/2017.
 */
@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public Client findById(int id) {
        return clientDao.findById(id);
    }

    @Override
    public Client findByName(String name) {
        return clientDao.findByName(name);
    }

    @Override
    public List<Client> findAllClients() {
        return clientDao.findAllClients();
    }

    @Override
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    public void updateClient(Client client) {
        Client entity = clientDao.findById(client.getId());
        if(entity != null) {
            entity.setClientName(client.getClientName());
            entity.setEmailID(client.getEmailID());
            entity.setClientContactNumber(client.getClientContactNumber());
            entity.setAddedBy(client.getAddedBy());
        }
    }

    @Override
    public void deleteById(int id) {
        clientDao.deleteById(id);
    }
}
