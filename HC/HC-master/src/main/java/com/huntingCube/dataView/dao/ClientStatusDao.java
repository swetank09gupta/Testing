package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ClientStatus;

import java.util.List;

/**
 * Created by guptado on 11/05/2017.
 */
public interface ClientStatusDao {

    ClientStatus findById(int id);

    ClientStatus findByStatusName(String name);

    List<ClientStatus> findAllStatus();

    void save(ClientStatus clientStatus);

    void deleteById(int id);
}
