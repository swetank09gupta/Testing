package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.Stream;

import java.util.List;

/**
 * Created by dgup27 on 1/21/2017.
 */
public interface StreamService {

    Stream findById(int id);

    Stream findByName(String name);

    List<Stream> findAllStreams();

    void save(Stream stream);

    void updateStream(Stream stream);

    void deleteById(int id);
}
