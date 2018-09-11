package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Stream;

import java.util.List;

/**
 * Created by guptado on 12/01/2017.
 */
public interface StreamDao {

    Stream findById(int id);

    Stream findByName(String name);

    List<Stream> findAllStreams();

    void save(Stream stream);

    void deleteById(int id);

}
