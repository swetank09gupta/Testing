package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Stream;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guptado on 12/01/2017.
 */
@Repository("streamDao")
public class StreamDaoImpl extends AbstractDao<Integer, Stream> implements StreamDao {
    @Override
    public Stream findById(int id) {
        return getByKey(id);
    }

    @Override
    public Stream findByName(String name) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("streamName", name.toUpperCase()));
        return (Stream) criteria.uniqueResult();
    }

    @Override
    public List<Stream> findAllStreams() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("streamName"));
        List<Stream> streamList = (List<Stream>) criteria.list();
        return streamList;
    }

    @Override
    public void save(Stream stream) {
        persist(stream);
    }

    @Override
    public void deleteById(int id) {
        Stream stream = findById(id);
        stream.setDeleted(true);
        saveOrUpdate(stream);
    }
}
