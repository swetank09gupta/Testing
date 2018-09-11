package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.StreamDao;
import com.huntingCube.dataView.model.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dgup27 on 1/21/2017.
 */
@Service("streamService")
@Transactional
public class StreamServiceImpl implements StreamService {

    @Autowired
    StreamDao streamDao;

    @Override
    public Stream findById(int id) {
        return streamDao.findById(id);
    }

    @Override
    public Stream findByName(String name) {
        return streamDao.findByName(name);
    }

    @Override
    public List<Stream> findAllStreams() {
        return streamDao.findAllStreams();
    }

    @Override
    public void save(Stream stream) {
        streamDao.save(stream);
    }

    @Override
    public void updateStream(Stream stream) {
        Stream entity = streamDao.findById(stream.getId());
        if(entity != null) {
            entity.setStreamName(stream.getStreamName());
            entity.setAddedBy(stream.getAddedBy());
        }
    }

    @Override
    public void deleteById(int id) {
        streamDao.deleteById(id);
    }
}
