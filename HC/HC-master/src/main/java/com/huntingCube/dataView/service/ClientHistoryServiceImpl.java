package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.ClientHistoryDao;
import com.huntingCube.dataView.model.ClientHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dgup27 on 5/7/2017.
 */
@Service("clientHistoryService")
@Transactional
public class ClientHistoryServiceImpl implements ClientHistoryService {

    @Autowired
    private ClientHistoryDao clientHistoryDao;

    @Override
    public ClientHistory findById(int id) {
        return clientHistoryDao.findById(id);
    }

/*    @Override
    public List<ClientHistory> findByClientID(String name) {
        return clientHistoryDao.findByClientID(name);
    }*/

    @Override
    public List<ClientHistory> findByRecruiter(String ssoID) {
        return clientHistoryDao.findByRecruiter(ssoID);
    }

    @Override
    public List<ClientHistory> findByResource(int resourceID) {
        return clientHistoryDao.findByResource(resourceID);
    }

    @Override
    public List<ClientHistory> findAll() {
        return clientHistoryDao.findAll();
    }

    @Override
    public List<ClientHistory> findByFilter(ClientHistory clientHistory) {
        return clientHistoryDao.findByFilter(clientHistory);
    }

    @Override
    public void save(ClientHistory clientHistory) {
        clientHistoryDao.save(clientHistory);
    }

    @Override
    public void deleteById(int id) {
        clientHistoryDao.deleteById(id);
    }
}
