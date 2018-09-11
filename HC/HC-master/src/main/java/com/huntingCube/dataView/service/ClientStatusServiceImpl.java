package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.ClientStatusDao;
import com.huntingCube.dataView.model.ClientStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by guptado on 11/05/2017.
 */
@Service("clientStatusService")
@Transactional
public class ClientStatusServiceImpl implements ClientStatusService {

    @Autowired
    ClientStatusDao clientStatusDao;

    @Override
    public ClientStatus findById(int id) {
        return clientStatusDao.findById(id);
    }

    @Override
    public ClientStatus findByStatusName(String name) {
        return clientStatusDao.findByStatusName(name);
    }

    @Override
    public List<ClientStatus> findAllStatus() {
        return clientStatusDao.findAllStatus();
    }

    @Override
    public void save(ClientStatus clientStatus) {
        clientStatusDao.save(clientStatus);
    }

    @Override
    public void updateClientStatus(ClientStatus clientStatus) {
        ClientStatus entity = clientStatusDao.findById(clientStatus.getId());
        if(entity != null) {
            entity.setClientStatusName(clientStatus.getClientStatusName());
            entity.setAddedBy(clientStatus.getAddedBy());
        }
    }

    @Override
    public void deleteById(int id) {
        clientStatusDao.deleteById(id);
    }
}
