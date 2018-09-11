package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.Client;

import java.util.List;

/**
 * Created by dgup27 on 5/7/2017.
 */
public interface ClientService {

    Client findById(int id);

    Client findByName(String name);

    List<Client> findAllClients();

    void save(Client client);

    void updateClient(Client client);

    void deleteById(int id);
}
