package com.euwebchallenge.krapoint.dao;

import com.euwebchallenge.krapoint.domain.Path;
import com.euwebchallenge.krapoint.domain.rest.PathRestRequest;

/**
 * IPathDAO Class
 *
 * @version 11/7/15
 */
public interface IPathDAO {
    String create(PathRestRequest entity);
    Path get(String id);
    Path get(String pointA, String pointB);
    boolean delete(String id);
    boolean delete(String pointA, String pointB);
    void update(String pointA, String pointB, int estimate);
    Path[] getAll();
}
