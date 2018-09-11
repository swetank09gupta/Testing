package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.ClientHistory;

import java.util.List;

/**
 * Created by dgup27 on 5/7/2017.
 */
public interface ClientHistoryService {

    ClientHistory findById(int id);

    /*List<ClientHistory> findByClientID(int ClientId);*/

    List<ClientHistory> findByRecruiter(String ssoID);

    List<ClientHistory> findByResource(int resourceID);

    List<ClientHistory> findAll();

    List<ClientHistory> findByFilter(ClientHistory clientHistory);

    void save(ClientHistory clientHistory);

    void deleteById(int id);
}
