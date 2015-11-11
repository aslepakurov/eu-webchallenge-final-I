package com.euwebchallenge.krapoint.service;

import com.euwebchallenge.krapoint.dao.IPathDAO;
import com.euwebchallenge.krapoint.domain.Path;
import com.euwebchallenge.krapoint.domain.rest.PathRestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PathService Class
 *
 * @version 11/7/15
 */
@Component
public class PathService implements IPathService {

    @Autowired
    private IPathDAO dao;

    @Override
    public String create(PathRestRequest entity) {
        return dao.create(entity);
    }

    @Override
    public Path get(String id) {
        return dao.get(id);
    }

    @Override
    public Path get(String pointA, String pointB) {
        return dao.get(pointA, pointB);
    }

    @Override
    public boolean delete(String id) {
        return dao.delete(id);
    }

    @Override
    public boolean delete(String pointA, String pointB) {
        return dao.delete(pointA, pointB);
    }

    @Override
    public void update(String pointA, String pointB, int estimate) {
        dao.update(pointA, pointB, estimate);
    }

    @Override
    public Path[] getAll() {
        return dao.getAll();
    }
}
